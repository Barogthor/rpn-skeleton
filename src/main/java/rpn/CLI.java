package rpn;

import rpn.Exceptions.UnsufficientArgumentException;
import rpn.Exceptions.UnsupportedExpressionException;
import rpn.Expression.Interpreter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        double result = 0;
        try {
            result = evaluate(expression);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("> " + result);
    }

    static double evaluate(String expression) throws UnsupportedExpressionException, UnsufficientArgumentException {
        return Interpreter.evaluate(expression);
    }
}
