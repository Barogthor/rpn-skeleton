package rpn3.conusmerThread;

import rpn3.Bus;
import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.Consumer;
import rpn3.messages.EndOfToken;
import rpn3.messages.TokenMessage;
import rpn3.messages.operations.EndOfOperation;

/**
 * @author
 * @since
 **/
public class CalculatorThread extends Thread {

    private Consumer consumer;

    public CalculatorThread(Bus bus) {
        this.consumer = new CalculatorConsumer(bus);
        bus.subscribe(TokenMessage.MESSAGE_TYPE, consumer);
        bus.subscribe(EndOfToken.MESSAGE_TYPE, consumer);
        bus.subscribe(EndOfOperation.MESSAGE_TYPE, consumer);

    }
}
