package rpn.Exceptions;

/**
 * @author
 * @since
 **/
public class UnsufficientArgumentException extends Exception {
    private final static String CLASSNAME = "rpn.Exceptions.UnsufficientArgumentException";

    public UnsufficientArgumentException() {
        super("Operation can't be executed with unsifficient number of operators");
    }
}
