package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.DropOperationMessage;
import rpn3.messages.operations.EndOfOperation;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class DropOperationConsumer implements Consumer {

    private Bus bus;

    public DropOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        DropOperationMessage eMsg = (DropOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        operands.pop();
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }
}
