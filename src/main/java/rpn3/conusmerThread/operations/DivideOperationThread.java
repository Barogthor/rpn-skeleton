package rpn3.conusmerThread.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.consumers.operations.DivideOperationConsumer;
import rpn3.consumers.operations.SubOperationConsumer;
import rpn3.messages.operations.DivideOperationMessage;

/**
 * @author
 * @since
 **/
public class DivideOperationThread extends Thread {

    private Consumer consumer;

    public DivideOperationThread(Bus bus) {
        this.consumer = new DivideOperationConsumer(bus);
        bus.subscribe(DivideOperationMessage.MESSAGE_TYPE, consumer);
    }
}
