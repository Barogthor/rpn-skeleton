package rpn3.consumers.operations;

import rpn3.Bus;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;
import rpn3.messages.operations.AddOperationMessage;
import rpn3.messages.operations.EndOfOperation;

import java.util.Stack;

/**
 * @author
 * @since
 **/
public class AddOperationConsumer implements Consumer {

    private Bus bus;

    public AddOperationConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        AddOperationMessage eMsg = (AddOperationMessage) message;
        Stack<Double> operands = (Stack<Double>) eMsg.getStack().clone();
        Double rightOperand = operands.pop(), leftOperand = operands.pop();
        operands.push(leftOperand+rightOperand);
        bus.publish(new EndOfOperation(operands, eMsg.id()));
    }

    @Override
    public void run() {

    }
}
