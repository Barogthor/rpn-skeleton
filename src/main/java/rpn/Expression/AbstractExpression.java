package rpn.Expression;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.ArrayDeque;

/**
 * @author
 * @since
 **/
public abstract class AbstractExpression {
    private final static String CLASSNAME = "rpn.Expression.AbstractExpression";

    public abstract void operate(String expression, ArrayDeque<Double> result) throws UnsufficientArgumentException;

}
