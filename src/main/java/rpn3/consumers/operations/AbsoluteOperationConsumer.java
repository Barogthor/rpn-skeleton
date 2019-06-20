package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.AbsoluteOperationMessage;
import rpn3.messages.operations.EndOfOperation;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class AbsoluteOperationConsumer implements Consumer {

    private Bus bus;

    public AbsoluteOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        AbsoluteOperationMessage eMsg = (AbsoluteOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        Double operand = operands.pop();
        operands.push(Math.abs(operand));
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }
}
