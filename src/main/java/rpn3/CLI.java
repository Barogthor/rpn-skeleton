package rpn3;

import rpn3.consumers.CalculatorConsumer;
import rpn3.consumers.ClientConsumer;
import rpn3.consumers.TokenizerConsumer;
import rpn3.consumers.operations.AddOperationConsumer;
import rpn3.consumers.operations.DivideOperationConsumer;
import rpn3.consumers.operations.MultiplyOperationConsumer;
import rpn3.consumers.operations.SubOperationConsumer;
import rpn3.conusmerThread.CalculatorThread;
import rpn3.conusmerThread.ClientThread;
import rpn3.conusmerThread.TokenizerThread;
import rpn3.conusmerThread.operations.AddOperationThread;
import rpn3.conusmerThread.operations.DivideOperationThread;
import rpn3.conusmerThread.operations.MultiplyOperationThread;
import rpn3.conusmerThread.operations.SubOperationThread;
import rpn3.messages.EndOfCalcul;
import rpn3.messages.EndOfToken;
import rpn3.messages.ExpressionMessage;
import rpn3.messages.TokenMessage;
import rpn3.messages.operations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CLI {

    public static final Bus BUS = initializeBus();

    private static Bus initializeBus(){
        return getBus();
    }

    static Bus getBus() {
        Bus bus = new InMemoryBus();

        Thread tokenizerThread = new TokenizerThread(bus);
        tokenizerThread.start();
        Thread calculatorThread = new CalculatorThread(bus);
        calculatorThread.start();
        Thread addOperationThread = new AddOperationThread(bus);
        addOperationThread.start();
        Thread subOperationThread = new SubOperationThread(bus);
        subOperationThread.start();
        Thread multiplyOperationThread = new MultiplyOperationThread(bus);
        multiplyOperationThread.start();
        Thread divivdeOperationThread = new DivideOperationThread(bus);
        divivdeOperationThread.start();

//        bus.subscribe(EndOfCalcul.MESSAGE_TYPE, new ClientConsumer(bus));
        return bus;
    }

    public static void subscribeClient(ClientConsumer clientConsumer){
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, clientConsumer);
    }

    public static void main(String... args) {

        ClientThread client1 = new ClientThread(BUS,"1 2 + 3 -",0);
        ClientThread client2 = new ClientThread(BUS,"1 2 + 4 - 2 *",-2);
        ClientThread client3 = new ClientThread(BUS,"1 2 + 4 - 2 * 4 /",-0.5);
        List<Thread> pool = new ArrayList<>();
        pool.add(new ClientThread(BUS,"5",5));
        pool.add(new ClientThread(BUS,"17",17));
        pool.add(new ClientThread(BUS,"17 5 +",12));
        pool.add(new ClientThread(BUS,"2 3 5 + +",10));
        pool.add(new ClientThread(BUS,"2 3 5 + + 9 -",1));
        pool.add(new ClientThread(BUS,"-2 6 *",-12));
        pool.add(new ClientThread(BUS,"2 6 4 * *",48));
        pool.add(new ClientThread(BUS,"-6 3 /",-2));
        pool.add(new ClientThread(BUS,"6 4 + 3 /",10/3f));
        pool.add(new ClientThread(BUS,"5.5 7.3 + 6.4 /",2));
        client1.start();
        client2.start();
        client3.start();
//        pool.get(0).start();
        pool.forEach(Thread::start);
//        client3.start();
//        thread3.run();
//        client1.sendExpression("1 2 + 3 -");
//        client2.sendExpression("1 2 + 3 - 2 *");
//        client3.sendExpression("1 2 + 4 - 2 * 4 /");

    }
}
