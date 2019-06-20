package rpn3.messages.operations;

import rpn3.MessageType;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class DivideOperationMessage extends OperationMessage {


    public static final MessageType MESSAGE_TYPE = MessageType.DIVIDE;

    public DivideOperationMessage(Stack<Double> stack, String expressionId) {
        super(stack, expressionId);
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }
}
