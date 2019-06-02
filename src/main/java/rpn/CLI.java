package rpn;

import rpn.Exceptions.UnsufficientArgumentException;
import rpn.Exceptions.UnsupportedExpressionException;
import rpn.Expression.Expression;
import rpn.Expression.Interpreter;
import rpn.Expression.Number;
import rpn.Expression.Operation.*;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    private final static HashMap<Pattern, Expression> operations = new HashMap<Pattern, Expression>() {{
        put(Pattern.compile("-?\\d+(\\.\\d+)?"), new Number());
        put(Pattern.compile("\\+"), new PlusOperation());
        put(Pattern.compile("-"), new SubstractOperation());
        put(Pattern.compile("\\*"), new MultiplyOperation());
        put(Pattern.compile("/"), new DivideOperation());
        put(Pattern.compile("ABS"), new AbsOperation());
        put(Pattern.compile("SWAP"), new SwapOperation());
    }};

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
        return Interpreter.evaluate(expression, operations);
    }
}
