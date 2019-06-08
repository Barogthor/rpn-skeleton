package rpn3;

import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.ClientConsumer;
import rpn3.consumers.Consumer;
import rpn3.consumers.TokenizerConsumer;
import rpn3.consumers.operations.AddOperationConsumer;
import rpn3.consumers.operations.DivideOperationConsumer;
import rpn3.consumers.operations.MultiplyOperationConsumer;
import rpn3.consumers.operations.SubOperationConsumer;
import rpn3.messages.EndOfCalcul;
import rpn3.messages.EndOfToken;
import rpn3.messages.ExpressionMessage;
import rpn3.messages.TokenMessage;
import rpn3.messages.operations.*;

public class CLI {

    public static final Bus BUS = initializeBus();

    private static Bus initializeBus(){
        return getBus();
    }

    static Bus getBus() {
        Bus bus = new InMemoryBus();
        bus.subscribe(ExpressionMessage.MESSAGE_TYPE, new TokenizerConsumer(bus));
        CalculatorConsumer consumer = new CalculatorConsumer(bus);
        bus.subscribe(TokenMessage.MESSAGE_TYPE, consumer);
        bus.subscribe(EndOfToken.MESSAGE_TYPE, consumer);
        bus.subscribe(EndOfOperation.MESSAGE_TYPE, consumer);
        bus.subscribe(AddOperationMessage.MESSAGE_TYPE, new AddOperationConsumer(bus));
        bus.subscribe(SubOperationMessage.MESSAGE_TYPE, new SubOperationConsumer(bus));
        bus.subscribe(MultiplyOperationMessage.MESSAGE_TYPE, new MultiplyOperationConsumer(bus));
        bus.subscribe(DivideOperationMessage.MESSAGE_TYPE, new DivideOperationConsumer(bus));

        return bus;
    }

    private static void subscribeClient(Consumer consumer) {
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, consumer);
    }

    public static void main(String... args) {


        ClientConsumer client1 = new ClientConsumer(BUS);
        subscribeClient(client1);
        ClientConsumer client2 = new ClientConsumer(BUS);
        subscribeClient(client2);
        ClientConsumer client3 = new ClientConsumer(BUS);
        subscribeClient(client3);
        client1.sendExpression("1 2 + 3 -");
        client2.sendExpression("1 2 + 4 - 2 *");
        client3.sendExpression("1 2 + 4 - 2 * 4 /");

    }
}
