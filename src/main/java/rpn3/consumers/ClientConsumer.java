package rpn3.consumers;

import rpn3.Bus;
import rpn3.messages.EndOfCalcul;
import rpn3.messages.ExpressionMessage;
import rpn3.messages.Message;

import java.text.MessageFormat;
import java.util.Stack;
import java.util.UUID;

/**
 * @author
 * @since
 **/
public class ClientConsumer implements Consumer, Runnable {
    private Bus bus;
    private String clientId;
    private Stack<Double> result;
    private String expression;
    private Double expected;

    public ClientConsumer(Bus bus, String expression, double expected) {
        this.bus = bus;
        this.clientId = UUID.randomUUID().toString();
        this.expression = expression;
        this.expected = expected;
    }
    public ClientConsumer(Bus bus) {
        this.bus = bus;
        this.clientId = UUID.randomUUID().toString();
        this.expression = "";
    }

    @Override
    public void receive(Message message) {
        EndOfCalcul eMsg = (EndOfCalcul) message;
//        System.out.println(clientId);
        System.out.println(MessageFormat.format("Result of ''{0}'' is {1} for client ''{2}''\nExpected: ''{3}'' is {4}",
                expression, eMsg.getResult(),clientId, expected,
                eMsg.getResult().size()==1 ? Math.abs(eMsg.getResult().peek()-expected)<0.00001 : "NaN"));
        result = eMsg.getResult();
    }

    public void sendExpression(String aExpression){
        bus.publish(new ExpressionMessage(aExpression,clientId));
    }
    public void sendExpression() { bus.publish(new ExpressionMessage(this.expression,clientId));}

    public Stack<Double> getResult() {
        return result;
    }

    @Override
    public void run() {
        sendExpression();
    }

    public boolean checkIdentityMessage(Message message){
        return message.id().equals(clientId);
    }
}
