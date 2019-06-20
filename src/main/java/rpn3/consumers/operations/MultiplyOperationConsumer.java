package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.EndOfOperation;
import rpn3.messages.operations.MultiplyOperationMessage;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class MultiplyOperationConsumer implements Consumer {

    private Bus bus;

    public MultiplyOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        MultiplyOperationMessage eMsg = (MultiplyOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        Double op2 = operands.pop(), op1 = operands.pop();
        operands.push(op2*op1);
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }
}
