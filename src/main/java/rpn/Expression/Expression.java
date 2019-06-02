package rpn.Expression;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public interface Expression {
    void operate(Stack<Double> result, String expression) throws UnsufficientArgumentException;
}
