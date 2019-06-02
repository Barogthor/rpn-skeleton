package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

public class DropOperation implements Operation {
    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if(result.size() < 1)
            throw new UnsufficientArgumentException();

        result.pop();
    }
}
