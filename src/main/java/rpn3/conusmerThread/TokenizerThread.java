package rpn3.conusmerThread;

import rpn3.Bus;
import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.Consumer;
import rpn3.consumers.TokenizerConsumer;
import rpn3.messages.ExpressionMessage;

/**
 * @author
 * @since
 **/
public class TokenizerThread extends Thread {

    private Consumer consumer;

    public TokenizerThread(Bus bus) {
        this.consumer = new TokenizerConsumer(bus);
        bus.subscribe(ExpressionMessage.MESSAGE_TYPE, consumer);
    }
}
