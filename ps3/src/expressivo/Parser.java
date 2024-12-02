package expressivo;


import java.util.Stack;
import java.util.regex.Pattern;


/**
 * Working
 * check if number
 * push in stack if number
 * if token push in stack
 * if parenthesis open loop till end of parenthesis
 * push in expression stack to generate AST
 */
public class Parser {

    public static Expression parse(String input) {
        String[] tokens = tokenize(input);
        Stack<Expression> expressions = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                expressions.push(new Number(Double.parseDouble(token)));
            } else if (isVariable(token)) {
                expressions.push(new Variable(token));
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
                }
                operators.pop();
            } else if (token.equals("+") || token.equals("*")) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            expressions.push(applyOperator(operators.pop(), expressions.pop(), expressions.pop()));
        }

        return expressions.pop();
    }

    private static String[] tokenize(String input) {
        return input.replaceAll("\\s+", "")
                    .replaceAll("([+*()])", " $1 ")
                    .trim()
                    .split("\\s+");
    }

    private static boolean isNumber(String token) {
        return Pattern.matches("\\d+(\\.\\d+)?", token);
    }

    private static boolean isVariable(String token) {
        return Pattern.matches("[a-zA-Z]+", token);
    }

    private static int precedence(String operator) {
        return operator.equals("+") ? 1 : (operator.equals("*") ? 2 : 0);
    }

    private static Expression applyOperator(String operator, Expression right, Expression left) {
        return operator.equals("+") ? new Sum(left, right) : new Product(left, right);
    }
}

