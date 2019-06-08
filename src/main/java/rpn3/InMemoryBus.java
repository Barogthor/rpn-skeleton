package rpn3;

import com.sun.org.apache.bcel.internal.generic.RET;
import rpn3.consumers.ClientConsumer;
import rpn3.consumers.Consumer;
import rpn3.messages.Message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class InMemoryBus implements Bus {

    private final Map<MessageType, List<Consumer>> consumersByType = new HashMap<>();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void publish(Message message) {
        List<Consumer> consumers = consumersByType.get(message.messageType());
        if(consumers==null)
            return;
//        logger.info(message.toString());

        consumers.forEach(c -> {
            if(message.messageType()==MessageType.EOC) {
                if(((ClientConsumer) c).checkIdentityMessage(message)) c.receive(message);
            }
            else
                c.receive(message);
        });
    }

    @Override
    public void subscribe(MessageType messageType, Consumer consumer) {
        List<Consumer> consumers = consumersByType.get(messageType);
        if(consumers == null) {
            consumers = new ArrayList<>();
            consumersByType.put(messageType, consumers);
        }
        consumers.add(consumer);
    }
}
