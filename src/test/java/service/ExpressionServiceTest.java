package service;

import exception.MathExpressionValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpressionServiceTest {

    private static final double DELTA = 0.0001;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ExpressionService expressionService;

    @Test
    void calculateMathExpression_WhenExpressionIsNotValid_ShouldThrowMathExpressionValidationException() {
        String expression = "2+3-5)";

        Mockito.when(validationService.isValidExpression(expression)).thenReturn(false);

        Assertions.assertThrows(MathExpressionValidationException.class,
                () -> expressionService.calculateMathExpression(expression));
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndStartWithBrackets_ShouldReturnCorrectResult() {
        String expression = "(2+3)-5";

        Mockito.when(validationService.isValidExpression(expression)).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 3) - 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidWithDifferentPriority_ShouldReturnCorrectResult() {
        String expression = "2+4*5";

        Mockito.when(validationService.isValidExpression(expression)).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals(2 + 4 * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidWithBrackets_ShouldReturnCorrectResult() {
        String expression = "(2+4)*5";

        Mockito.when(validationService.isValidExpression(expression)).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndStartsWithSpaces_ShouldReturnCorrectResult() {
        String expression = "(2+4)*5   ";

        Mockito.when(validationService.isValidExpression(expression.trim())).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndEndsWithSpaces_ShouldReturnCorrectResult() {
        String expression = "   (2+4)*5";

        Mockito.when(validationService.isValidExpression(expression.trim())).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

    @Test
    void calculateMathExpression_WhenExpressionIsValidAndContainsSpaces_ShouldReturnCorrectResult() {
        String expression = "(2 +   4) *  5";

        Mockito.when(validationService.isValidExpression(expression.trim())).thenReturn(true);

        double actual = expressionService.calculateMathExpression(expression);

        Assertions.assertEquals((2 + 4) * 5, actual, DELTA);
    }

}