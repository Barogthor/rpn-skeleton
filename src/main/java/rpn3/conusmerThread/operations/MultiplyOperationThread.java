package rpn3.conusmerThread.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.consumers.operations.MultiplyOperationConsumer;
import rpn3.consumers.operations.SubOperationConsumer;
import rpn3.messages.operations.MultiplyOperationMessage;

/**
 * @author
 * @since
 **/
public class MultiplyOperationThread extends Thread {

    private Consumer consumer;

    public MultiplyOperationThread(Bus bus) {
        this.consumer = new MultiplyOperationConsumer(bus);
        bus.subscribe(MultiplyOperationMessage.MESSAGE_TYPE, consumer);
    }
}
