package rpn3.messages;

import rpn3.MessageType;

public class TokenMessage implements Message {
    public static final MessageType MESSAGE_TYPE = MessageType.TOKEN;

    private final String token;

    public String getToken() {
        return token;
    }

    private final String expressionId;

    public TokenMessage(String token, String expressionId) {
        this.token = token;
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
        return "TokenMessage{" +
                "token='" + token + '\'' +
                ", expressionId='" + expressionId + '\'' +
                '}';
    }
}
