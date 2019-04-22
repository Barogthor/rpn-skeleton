package rpn.Exceptions;

/**
 * @author
 * @since
 **/
public class UnsupportedExpressionException extends Exception {
    private final static String CLASSNAME = "rpn.Exceptions.UnsupportedExpressionException";

    public UnsupportedExpressionException() {
        super("Expression unknown");
    }
}
