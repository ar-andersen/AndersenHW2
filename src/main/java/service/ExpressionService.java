package service;

import exception.MathExpressionValidationException;

import java.util.ArrayDeque;
import java.util.Deque;

//TODO Make algorithm work with non-positive numbers
public class ExpressionService {

    private final ValidationService validationService;

    public ExpressionService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public double calculateMathExpression(String expression) {
        expression = expression.trim();
        String rpnExpression = mathExpressionToRPN(expression);
        return calculateRPNExpression(rpnExpression);
    }

    private String mathExpressionToRPN(String mathExpression) {
        if (!validationService.isValidExpression(mathExpression)) {
            throw new MathExpressionValidationException("Expression is not valid");
        }

        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder number = new StringBuilder();

        for (char ch : mathExpression.toCharArray()) {
            if (Character.isDigit(ch)) {
                number.append(ch);
            } else if (!Character.isSpaceChar(ch)) {
                appendNumberToResult(result, number);
                processOperator(ch, result, stack);
            }
        }

        appendNumberToResult(result, number);
        appendRemainingOperatorsToResult(result, stack);

        return result.toString();
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    private void appendNumberToResult(StringBuilder result, StringBuilder number) {
        if (!number.isEmpty()) {
            result.append(number).append(" ");
            number.setLength(0);
        }
    }

    private void processOperator(char ch, StringBuilder result, Deque<Character> stack) {
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                result.append(stack.pop());
            }
            stack.pop();
        } else {
            while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                result.append(stack.pop());
            }
            stack.push(ch);
        }
    }

    private void appendRemainingOperatorsToResult(StringBuilder result, Deque<Character> stack) {
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
    }

    private double calculateRPNExpression(String rpnExpression) {
        Deque<Double> st = new ArrayDeque<>();
        StringBuilder number = new StringBuilder();
        for (char ch : rpnExpression.toCharArray()) {
            if (ch == '+') {
                double a = st.pop();
                double b = st.pop();
                st.push(a + b);
            } else if (ch == '-') {
                double a = st.pop();
                double b = st.pop();
                st.push(b - a);
            } else if (ch == '*') {
                double a = st.pop();
                double b = st.pop();
                st.push(a * b);
            } else if (ch == '/') {
                double a = st.pop();
                double b = st.pop();
                st.push(b / a);
            } else if (Character.isDigit(ch)) {
                number.append(ch);
            } else {
                st.push(Double.parseDouble(number.toString()));
                number = new StringBuilder();
            }
        }

        return st.peek();
    }

}
