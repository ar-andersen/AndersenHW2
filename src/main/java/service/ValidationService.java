package service;

public class ValidationService {
    private static final String OPERATOR_REGEX = ".*[-+*/].*";
    private static final String OPERAND_REGEX = ".*\\d+.*";
    private static final String STARTING_EXPRESSION_REGEX = "^(\\d|\\().*";
    private static final String ENDING_EXPRESSION_REGEX = "^(\\d|\\().*(\\d|\\))$";
    private static final String ONLY_VALID_SYMBOLS_REGEX = "^[-+*/\\d\\s()]*$";

    public boolean isValidExpression(String expression) {
        if (!isValidOperatorsAndOperands(expression) || !isValidExpressionStartAndEnd(expression)) {
            return false;
        }

        return expression.matches(ONLY_VALID_SYMBOLS_REGEX) && isValidBrackets(expression);
    }

    private boolean isValidExpressionStartAndEnd(String expression) {
        return expression.matches(STARTING_EXPRESSION_REGEX) && expression.matches(ENDING_EXPRESSION_REGEX);
    }

    private boolean isValidOperatorsAndOperands(String expression) {
        return expression.matches(OPERAND_REGEX) && expression.matches(OPERATOR_REGEX);
    }

    private boolean isValidBrackets(String expression) {
        int count = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

}
