package rpn.Expression.Operation;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

public class TimeOperation implements Operation {
    public void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException {
        if (result.size() < 2)
            throw new UnsufficientArgumentException();

        double rightOperand = result.pop(), leftOperand = result.pop();
        for (int i = 0; i < rightOperand; i++) {
            System.out.println(leftOperand);
            result.push(leftOperand);
        }
    }
}
