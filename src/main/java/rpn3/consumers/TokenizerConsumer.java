package rpn3.consumers;

import rpn3.Bus;
import rpn3.messages.EndOfToken;
import rpn3.messages.ExpressionMessage;
import rpn3.messages.Message;
import rpn3.messages.TokenMessage;

import java.util.stream.Stream;

public class TokenizerConsumer implements Consumer {

    private final Bus bus;

    public TokenizerConsumer(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void receive(Message message) {
        ExpressionMessage eMsg = (ExpressionMessage) message;

        String expression = eMsg.expression();
        Stream.of(expression.split("\\s+"))
                .forEach(token -> bus.publish(
                        new TokenMessage(token, eMsg.id())));

        bus.publish(new EndOfToken(eMsg.id()));
    }

    @Override
    public void run() {

    }
}
