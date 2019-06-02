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

    public static double evaluate(String expression, HashMap<Pattern, Expression> operations) throws UnsupportedExpressionException, UnsufficientArgumentException {
        Stack<Double> result = new Stack<>();

        for(String token : expression.split("\\s"))
            whichExpression(token, operations).operate(result, token);

        return result.pop();
    }

    private static Expression whichExpression(String token, HashMap<Pattern, Expression> operations) throws UnsupportedExpressionException {
        return operations.entrySet().stream()
                .filter(e -> e.getKey().matcher(token).matches())
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(() -> new UnsupportedExpressionException(token));
    }
}
