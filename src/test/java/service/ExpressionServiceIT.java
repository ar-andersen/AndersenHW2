package service;

import exception.MathExpressionValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionServiceIT {

    private static final double DELTA = 0.0001;

    private final ValidationService validationService = new ValidationService();
    private final ExpressionService expressionService = new ExpressionService(validationService);

    @Test
    void calculateMathExpression_WhenExpressionIsNotValid_ShouldThrowMathExpressionValidationException() {
        String expression = "2+3-5)";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndStartWithBrackets_ShouldReturnCorrectResult() {
        String expression = "(2+3)-5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 3) - 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidWithDifferentPriority_ShouldReturnCorrectResult() {
        String expression = "2+4*5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals(2 + 4 * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidWithBrackets_ShouldReturnCorrectResult() {
        String expression = "(2+4)*5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndStartsWithSpaces_ShouldReturnCorrectResult() {
        String expression = "(2+4)*5   ";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndEndsWithSpaces_ShouldReturnCorrectResult() {
        String expression = "   (2+4)*5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndContainsSpaces_ShouldReturnCorrectResult() {
        String expression = "(2 +   4) *  5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionContainsInvalidCharacter_ShouldThrowMathExpressionValidationException() {
        String expression = "(2+4)=5";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionContainsInvalidBracketsCount_ShouldThrowMathExpressionValidationException() {
        String expression = "(2+4)+5)";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionEndsWithOperator_ShouldThrowMathExpressionValidationException() {
        String expression = "(2+4)+5*";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionStartsWithOperator_ShouldThrowMathExpressionValidationException() {
        String expression = "+(2+4)+5";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionContainsBracketsInIncorrectOrder_ShouldThrowMathExpressionValidationException() {
        String expression = ")2+4(+5";

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionContainsValidBracketsCount_ShouldReturnCorrectResult() {
        String expression = "(2+4)*5";

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

}