package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

public class SwapOperation implements Operation {
    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if(result.size() < 2)
            throw new UnsufficientArgumentException();

        double rightOperand = result.pop(), leftOperand = result.pop();
        result.push(rightOperand);
        result.push(leftOperand);
    }
}
