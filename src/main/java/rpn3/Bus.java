package rpn3;

import rpn3.consumers.Consumer;
import rpn3.messages.Message;

public interface Bus {
    void publish(Message message);
    void subscribe(MessageType messageType, Consumer consumer);
}
