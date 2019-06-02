package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class MultiplyOperation implements Operation {
    private final static String CLASSNAME = "rpn.Expression.Operation.MultiplyOperation";

    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if(result.size()<2)
            throw new UnsufficientArgumentException();
        result.push( result.pop() * result.pop() );
    }
}
