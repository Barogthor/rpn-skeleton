package rpn3.consumers;

import org.omg.CORBA.portable.Streamable;
import rpn3.messages.Message;

public interface Consumer extends Runnable {
    void receive(Message message);
}
