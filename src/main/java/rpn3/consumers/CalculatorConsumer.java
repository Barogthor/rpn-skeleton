package rpn3.consumers;

import rpn3.Bus;
import rpn3.MessageType;
import rpn3.messages.EndOfCalcul;
import rpn3.messages.EndOfToken;
import rpn3.messages.Message;
import rpn3.messages.TokenMessage;
import rpn3.messages.operations.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * @author
 * @since
 **/
public class CalculatorConsumer implements Consumer {

    private final static Map<Pattern, BiFunction<Stack<Double>, String, Message>> operationsType = initExpressionsMap();
    private final static Pattern NUMBER_REGEX = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final Bus bus;
    private Map<String, Stack<Double>> calculMap = new HashMap<>();
    private Map<String, Queue<Message>> tokenQueue = new HashMap<>();

    public CalculatorConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public synchronized void receive(Message message) {

        if(!calculMap.containsKey(message.id()))
            calculMap.put(message.id(), new Stack<>());
        if(!tokenQueue.containsKey(message.id()))
            tokenQueue.put(message.id(),new LinkedList<>());

        if(message instanceof EndOfToken){
            receiveEndOfToken((EndOfToken) message);
            return;
        }
        else if (message instanceof EndOfOperation){
            receiveEndOfOperation((EndOfOperation) message);
            return;
        }

        TokenMessage eMsg = (TokenMessage) message;
        if(NUMBER_REGEX.matcher(eMsg.getToken()).matches())
            calculMap.get(eMsg.id()).push(Double.parseDouble(eMsg.getToken()));
        else{
            bus.publish( getMessageOperator(eMsg));
        }
    }

    private void receiveToken(TokenMessage message){

    }

    private void receiveEndOfOperation(EndOfOperation message){
//        System.out.println(message.getStack().toString());
//        System.out.println(calculMap.get(message.id()).toString());
        calculMap.put(message.id(),message.getStack());
    }

    private void receiveEndOfToken(EndOfToken message){
//        System.out.println(calculMap.get(message.id()).toString());
        bus.publish(new EndOfCalcul(calculMap.get(message.id()),message.id()));
        calculMap.remove(message.id());
    }

    private Message getMessageOperator(TokenMessage eMsg) {
        return operationsType.entrySet().stream()
                .filter(e -> e.getKey().matcher(eMsg.getToken()).matches())
                .map(Map.Entry::getValue)
                .findAny()
                .get()
                .apply(calculMap.get(eMsg.id()),eMsg.id());
    }


    private static Map<Pattern, BiFunction<Stack<Double>,String,Message>> initExpressionsMap(){
        Map<Pattern, BiFunction<Stack<Double>,String,Message>> map = new HashMap<>();
//        map.put(Pattern.compile("-?\\d+(\\.\\d+)?"), null);
        map.put(Pattern.compile("\\+"), AddOperationMessage::new);
        map.put(Pattern.compile("-"), SubOperationMessage::new);
        map.put(Pattern.compile("\\*"), MultiplyOperationMessage::new);
        map.put(Pattern.compile("/"), DivideOperationMessage::new);
//        map.put(Pattern.compile("ABS"), MessageType.ADD);
//        map.put(Pattern.compile("SWAP"), MessageType.ADD);
//        map.put(Pattern.compile("DROP"), MessageType.ADD);
//        map.put(Pattern.compile("TIME"), MessageType.ADD);
        return map;
    }

    @Override
    public void run() {

    }
}
