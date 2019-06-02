package rpn.Expression;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class Number implements Expression {

    public void operate(Stack<Double> numbers, String expression) {
        numbers.push(Double.parseDouble(expression));
    }
}
