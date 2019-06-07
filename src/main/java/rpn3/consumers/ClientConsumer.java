package rpn3.consumers;

import rpn3.Bus;
import rpn3.messages.EndOfCalcul;
import rpn3.messages.ExpressionMessage;
import rpn3.messages.Message;

import java.util.Stack;
import java.util.UUID;

/**
 * @author
 * @since
 **/
public class ClientConsumer implements Consumer {
    private Bus bus;
    private String clientId;
    private Stack<Double> result;

    public ClientConsumer(Bus bus) {
        this.bus = bus;
        this.clientId = UUID.randomUUID().toString();
    }

    @Override
    public void receive(Message message) {
        EndOfCalcul eMsg = (EndOfCalcul) message;
        System.out.println(eMsg.getResult());
        result = eMsg.getResult();
        System.out.println(result);
    }

    public void sendExpression(String expression){
        bus.publish(new ExpressionMessage(expression,clientId));
    }

    public Stack<Double> getResult() {
        return result;
    }
}
