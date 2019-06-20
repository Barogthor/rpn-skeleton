package rpn3.messages;

import rpn3.MessageType;

public class ExpressionMessage implements Message {
    public static final MessageType MESSAGE_TYPE = MessageType.EXPRESSION;
    private final String expression;
    private final String expressionId;

    public ExpressionMessage(String expression, String expressionId) {
        this.expression = expression;
        this.expressionId = expressionId;
    }

    @Override
    public MessageType messageType() {
        return MESSAGE_TYPE;
    }

    public String expression() {
        return expression;
    }
    public String id() {
        return expressionId;
    }

    @Override
    public String toString() {
        return "ExpressionMessage{" +
                "expression='" + expression + '\'' +
                ", expressionId='" + expressionId + '\'' +
                '}';
    }
}
