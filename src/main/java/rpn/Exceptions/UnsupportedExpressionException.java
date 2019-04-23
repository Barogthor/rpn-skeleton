package rpn.Exceptions;

/**
 * @author
 * @since
 **/
public class UnsupportedExpressionException extends Exception {
    private final static String CLASSNAME = "rpn.Exceptions.UnsupportedExpressionException";

    public UnsupportedExpressionException(String expr) {
        super("Expression unknown: "+expr);
    }
}
