package rpn3.messages.operations;

import rpn3.MessageType;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class AddOperationMessage extends OperationMessage {


    public static final MessageType MESSAGE_TYPE = MessageType.ADD;

    public AddOperationMessage(Stack<Double> stack, String expressionId) {
        super(stack, expressionId);
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }
}
