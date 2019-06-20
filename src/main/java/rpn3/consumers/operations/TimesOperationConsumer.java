package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.EndOfOperation;
import rpn3.messages.operations.TimesOperationMessage;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class TimesOperationConsumer implements Consumer {

    private Bus bus;

    public TimesOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        TimesOperationMessage eMsg = (TimesOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        Double op2 = operands.pop(), op1 = operands.pop();
        for (int i = 0; i < op2; i++) {
            operands.push(op1);
        }
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }
}
