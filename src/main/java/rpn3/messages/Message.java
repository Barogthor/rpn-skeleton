package rpn3.messages;

import rpn3.MessageType;

public interface Message {
    MessageType messageType();
    String id();
}
