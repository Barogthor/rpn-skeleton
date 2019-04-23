package rpn.Expression;

import rpn.Exceptions.UnsufficientArgumentException;
import rpn.Exceptions.UnsupportedExpressionException;
import rpn.Expression.Operation.DivideOperation;
import rpn.Expression.Operation.MultiplyOperation;
import rpn.Expression.Operation.PlusOperation;
import rpn.Expression.Operation.SubstractOperation;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author
 * @since
 **/
public class Interpreter {
    private final static String CLASSNAME = "rpn.Expression.Interpreter";
    private final static HashMap<Pattern,AbstractExpression> operations = new HashMap<Pattern, AbstractExpression>() {{
        put(Pattern.compile("\\d+(\\.\\d+)?"), new Number());
        put(Pattern.compile("\\+"), new PlusOperation());
        put(Pattern.compile("-"), new SubstractOperation());
        put(Pattern.compile("\\*"), new MultiplyOperation());
        put(Pattern.compile("/"), new DivideOperation());
    }};

    public static double evaluate(String expression) throws UnsupportedExpressionException, UnsufficientArgumentException {
        ArrayDeque<String> elements = new ArrayDeque<>(Arrays.asList(expression.split(" ")));
        ArrayDeque<Double> stack = new ArrayDeque<>();

        while(!elements.isEmpty()){
            String element = elements.pop();
            whichExpression(element).operate(element,stack);
        }
        return stack.pop();
    }

    private static AbstractExpression whichExpression(String expression) throws UnsupportedExpressionException {
        for(Map.Entry<Pattern,AbstractExpression> entry : operations.entrySet())
            if(entry.getKey().matcher(expression).matches())
                return entry.getValue();
        throw new UnsupportedExpressionException(expression);
    }
}
