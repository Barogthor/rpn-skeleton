package rpn3.messages.operations;

import rpn3.MessageType;
import rpn3.messages.Message;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class EndOfOperation extends OperationMessage {


    public static final MessageType MESSAGE_TYPE = MessageType.EOOP;

    public EndOfOperation(Stack<Double> stack, String expressionId) {
        super(stack, expressionId);
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }

}
