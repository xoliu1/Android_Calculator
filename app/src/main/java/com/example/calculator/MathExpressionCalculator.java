package com.example.calculator;

import java.math.BigDecimal;
import java.util.Stack;

import java.util.Stack;

public class MathExpressionCalculator {

    public static double calculate(String expression) {
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (ch == '(') {
                operatorStack.push(ch);
                i++;
            } else if (ch == ')') {
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.pop();
                i++;
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(ch, operatorStack.peek())) {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(ch);
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                operandStack.push(Double.parseDouble(sb.toString()));
            }
        }

        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        double op2 = operandStack.pop();
        double op1 = operandStack.pop();
        double result = 0;
        switch (op) {
            case '+': result = op1 + op2; break;
            case '-': result = op1 - op2; break;
            case '*': result = op1 * op2; break;
            case '/': result = op1 / op2; break;
        }
        operandStack.push(result);
    }
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}