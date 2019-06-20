package rpn3.messages.operations;

import rpn3.MessageType;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class SubOperationMessage extends OperationMessage {


    public static final MessageType MESSAGE_TYPE = MessageType.SUB;

    public SubOperationMessage(Stack<Double> stack, String expressionId) {
        super(stack, expressionId);
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }
}
