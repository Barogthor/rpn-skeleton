package rpn3.messages.operations;

import rpn3.MessageType;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class AbsoluteOperationMessage extends OperationMessage {


    public static final MessageType MESSAGE_TYPE = MessageType.ABS;

    public AbsoluteOperationMessage(Stack<Double> stack, String expressionId) {
        super(stack, expressionId);
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }
}
