package rpn3.messages;

import rpn3.MessageType;

public class EndOfToken implements Message {

    public static final MessageType MESSAGE_TYPE = MessageType.EOT;
    private String expressionId;

    public EndOfToken(String expressionId) {
        this.expressionId = expressionId;
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }

    @Override
    public String id() {
        return expressionId;
    }

    @Override
    public String toString() {
        return "EndOfToken{" +
                "expressionId='" + expressionId + '\'' +
                '}';
    }
}
