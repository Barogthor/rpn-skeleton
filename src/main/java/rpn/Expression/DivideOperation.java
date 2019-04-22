package rpn.Expression;

import rpn.Exceptions.UnsufficientArgumentException;

import java.util.ArrayDeque;

/**
 * @author
 * @since
 **/
public class DivideOperation extends AbstractOperation {
    private final static String CLASSNAME = "rpn.Expression.DivideOperation";


    @Override
    public void operate(String expression, ArrayDeque<Long> result) throws UnsufficientArgumentException {
        if(result.size()<2)
            throw new UnsufficientArgumentException();

        result.push( result.pop() / result.pop() );

    }
}
