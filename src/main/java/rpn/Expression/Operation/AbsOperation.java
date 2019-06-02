package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

public class AbsOperation implements Operation {
    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if(result.size() < 1)
            throw new UnsufficientArgumentException();

        double operand = result.pop();

        result.push(Math.abs(operand));
    }
}
