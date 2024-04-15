package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    void isValidExpression_WhenExpressionStartsWithOperator_ShouldReturnFalse() {
        String expression = "+5-3";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertFalse(actual);
    }

    @Test
    void isValidExpression_WhenExpressionEndsWithOperator_ShouldReturnFalse() {
        String expression = "5-3*";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertFalse(actual);
    }

    @Test
    void isValidExpression_WhenExpressionContainsInvalidSymbol_ShouldReturnFalse() {
        String expression = "5-3^";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertFalse(actual);
    }

    @Test
    void isValidExpression_WhenExpressionContainsInvalidBracketsCount_ShouldReturnFalse() {
        String expression = "(5-3+(5-2)";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertFalse(actual);
    }

    @Test
    void isValidExpression_WhenExpressionContainsValidBracketsCount_ShouldReturnTrue() {
        String expression = "5-(3+5)-2";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertTrue(actual);
    }

    @Test
    void isValidExpression_WhenExpressionStartsWithBracketAndContainsValidBracketsCount_ShouldReturnTrue() {
        String expression = "(5-3)+5-2";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertTrue(actual);
    }

    @Test
    void isValidExpression_WhenExpressionEndsWithBracketAndContainsValidBracketsCount_ShouldReturnTrue() {
        String expression = "5-3+(5-2)";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertTrue(actual);
    }

    @Test
    void isValidExpression_WhenExpressionContainsBracketsInIncorrectOrder_ShouldReturnFalse() {
        String expression = "5-)3+5(-2";

        boolean actual = validationService.isValidExpression(expression);

        Assertions.assertFalse(actual);
    }

}