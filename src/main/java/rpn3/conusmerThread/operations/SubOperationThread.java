package rpn3.conusmerThread.operations;

import rpn3.Bus;
import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.Consumer;
import rpn3.consumers.operations.SubOperationConsumer;
import rpn3.messages.operations.SubOperationMessage;

/**
 * @author
 * @since
 **/
public class SubOperationThread extends Thread {

    private Consumer consumer;

    public SubOperationThread(Bus bus) {
        this.consumer = new SubOperationConsumer(bus);
        bus.subscribe(SubOperationMessage.MESSAGE_TYPE, consumer);
    }
}
