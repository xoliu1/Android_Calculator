package com.example.calculator;

import java.util.Stack;

public class Check {

    public static boolean validate(String expression) {
        int temp = 0;
        if (expression.contains("---") || expression.contains("----") || expression.contains("-----") || expression.contains("------")){
            return false;
        }
        char[] str = expression.toCharArray();
        if (str[0] == '/' || str[0] == '*')
            return false;
        if (str[str.length - 1] < '0' && str[str.length - 1] > '9')
            return false;
        //先进行括号判断
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                if (i >= str.length - 2) return false;
                if (str[i + 1] == '*' || str[i + 1] == '/' || str[i + 1] == '-' || str[i + 1] == '+' || str[i + 1] == '.')
                    //括号且紧接着*/
                    return false;
                if (i > 0 && str[i - 1] != '*' && str[i - 1] != '/' && str[i - 1] != '-' && str[i - 1] != '+' && str[i - 1] != '(')
                    return false;
                if (i > 0 && str[i - 1] == '.'){
                    return false;
                }
                temp++;
            } else if (str[i] == ')') {
                if (i == 0)
                    return false;
                if (str[i - 1] == '+' || str[i - 1] == '*' || str[i - 1] == '-' || str[i - 1] == '/')
                    return false;
                if (i <= str.length - 2 && str[i + 1] != '*' && str[i + 1] != '/' && str[i + 1] != '-' && str[i + 1] != '+' && str[i + 1] != ')')
                    return false;
                if (str[i - 1] == '.'){
                    return false;
                }
                temp--;
            }
        }
        if (temp == 0) {
            return true;
        } else {
            return false;
        }
    }


}
