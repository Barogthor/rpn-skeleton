package rpn3.conusmerThread.operations;

import rpn3.Bus;
import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.Consumer;
import rpn3.consumers.operations.AddOperationConsumer;
import rpn3.messages.operations.AddOperationMessage;

/**
 * @author
 * @since
 **/
public class AddOperationThread extends Thread {

    private Consumer consumer;

    public AddOperationThread(Bus bus) {
        this.consumer = new AddOperationConsumer(bus);

        bus.subscribe(AddOperationMessage.MESSAGE_TYPE, consumer);
    }
}
