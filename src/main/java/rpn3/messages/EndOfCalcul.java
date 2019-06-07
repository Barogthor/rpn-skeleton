package rpn3.messages;

import rpn3.MessageType;

import java.util.Stack;

public class EndOfCalcul implements Message {

    public static final MessageType MESSAGE_TYPE = MessageType.EOC;
    private final Stack<Double> result;
    private String expressionId;

    public EndOfCalcul(Stack<Double> result, String expressionId) {
        this.expressionId = expressionId;
        this.result = result;
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public String id() {
        return expressionId;
    }

    public Stack<Double> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "EndOfCalcul{" +
                "result=" + result +
                ", expressionId='" + expressionId + '\'' +
                '}';
    }
}
