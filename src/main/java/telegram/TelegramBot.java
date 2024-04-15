package telegram;

import config.BotConfig;
import exception.MathExpressionValidationException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.ExpressionService;

public class TelegramBot extends TelegramLongPollingBot {

    private static final String WELCOME_MESSAGE = """
            Welcome to my bot! This bot can calculate your math expression.
            Expression examples:
                1) (13+6)*8
                2) 4-2
                3) 56/2+6
            Note that bot works only with positive numbers.
            """;
    private final ExpressionService expressionService;

    public TelegramBot(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            try {
                if (messageText.equals("/start")) {
                    sendMessage(chatId, WELCOME_MESSAGE);
                    return;
                }
                double result = expressionService.calculateMathExpression(messageText);
                sendMessage(chatId, String.valueOf(result));
            } catch (MathExpressionValidationException e) {
                sendMessage(chatId, e.getMessage());
            }
        }
    }

    private void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKEN;
    }
}
