package rpn3.messages.operations;

import rpn3.MessageType;
import rpn3.messages.Message;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public abstract class OperationMessage implements Message {

    protected final String expressionId;
    protected final Stack<Double> stack;

    public OperationMessage(Stack<Double> stack, String expressionId){
        this.stack = stack;
        this.expressionId = expressionId;
    }

    @Override
    public abstract MessageType messageType();

    @Override
    public String id() {
        return expressionId;
    }

    public Stack<Double> getStack() {
        return stack;
    }

    @Override
    public String toString() {
        return "OperationMessage{" +
                "expressionId='" + expressionId + '\'' +
                ", stack=" + stack +
                '}';
    }
}
