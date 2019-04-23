package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.ArrayDeque;

/**
 * @author
 * @since
 **/
public class DivideOperation extends AbstractOperation {
    private final static String CLASSNAME = "rpn.Expression.Operation.DivideOperation";

    @Override
    public void operate(String expression, ArrayDeque<Double> result) throws UnsufficientArgumentException {
        if(result.size()<2)
            throw new UnsufficientArgumentException();

        double rightOperand = result.pop(), leftOperand = result.pop();
        result.push( leftOperand / rightOperand );
    }
}
