package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class DivideOperation implements Operation{

    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if(result.size()<2)
            throw new UnsufficientArgumentException();

        double rightOperand = result.pop(), leftOperand = result.pop();
        result.push( leftOperand / rightOperand );
    }
}
