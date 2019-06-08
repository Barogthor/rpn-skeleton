package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.EndOfOperation;
import rpn3.messages.operations.SwapOperationMessage;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class SwapOperationConsumer implements Consumer {

    private Bus bus;

    public SwapOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        SwapOperationMessage eMsg = (SwapOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        Double op2 = operands.pop(), op1 = operands.pop();
        operands.push(op2);
        operands.push(op1);
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }
}
