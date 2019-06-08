package rpn3.conusmerThread;

import rpn3.Bus;
import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.ClientConsumer;
import rpn3.consumers.Consumer;
import rpn3.messages.EndOfCalcul;

/**
 * @author
 * @since
 **/
public class ClientThread extends Thread {

    private Bus bus;
    private ClientConsumer consumer;

    public ClientThread(Bus bus,String expression,double expected) {
        this.bus = bus;
        this.consumer = new ClientConsumer(this.bus,expression,expected);
    }

    @Override
    public synchronized void start() {
        bus.subscribe(EndOfCalcul.MESSAGE_TYPE, consumer);
        super.start();
    }

    @Override
    public void run() {
        consumer.sendExpression();
        super.run();
    }
}
