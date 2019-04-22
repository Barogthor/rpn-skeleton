package rpn.Expression;

import java.util.ArrayDeque;

/**
 * @author
 * @since
 **/
public class Number extends AbstractExpression {
    private final static String CLASSNAME = "rpn.Expression.Number";


    @Override
    public void operate(String expression, ArrayDeque<Long> result) {
        result.push(Long.parseLong(expression));
    }
}
