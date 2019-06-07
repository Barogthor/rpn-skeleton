package rpn3.consumers;

import rpn3.messages.Message;

public interface Consumer {
    void receive(Message message);
}
