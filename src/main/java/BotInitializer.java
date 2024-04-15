import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.ExpressionService;
import service.ValidationService;
import telegram.TelegramBot;

public class BotInitializer {

    public static void main(String[] args) {
        try {
            ValidationService validationService = new ValidationService();
            ExpressionService expressionService = new ExpressionService(validationService);
            TelegramBot telegramBot = new TelegramBot(expressionService);

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
