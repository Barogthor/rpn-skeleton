package rpn3;

import org.assertj.core.data.Offset;
import org.junit.Test;
import rpn3.consumers.ClientConsumer;
import rpn3.messages.EndOfCalcul;

import static org.assertj.core.api.Assertions.assertThat;

public class Rpn3TestSynchrone {


    public static final Bus BUS = initializeBus();

    private static Bus initializeBus() {
        return CLI.getBus();
    }


    @Test
    public void should_evaluate_single_digit_constant() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("5");

        assertThat(client.getResult().pop()).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("17");

        assertThat(client.getResult().pop()).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("17 5 +");

        assertThat(client.getResult().pop()).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 3 5 + +");

        assertThat(client.getResult().pop()).isEqualTo(10);
    }

    @Test
    public void should_evaluate_more_complex_addition_with_substract() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 3 5 + + 9 -");

        assertThat(client.getResult().pop()).isEqualTo(1);
    }

    @Test
    public void should_evaluate_multiply() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("-2 6 *");

        assertThat(client.getResult().pop()).isEqualTo(-12);
    }

    @Test
    public void should_evaluate_multiply_more_complex() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 6 4 * *");

        assertThat(client.getResult().pop()).isEqualTo(48);
    }

    @Test
    public void shoud_evaluate_divide() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("-6 3 /");

        assertThat(client.getResult().pop()).isEqualTo(-2);
    }

    @Test
    public void shoud_evaluate_divide_float() {

        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("6 4 + 3 /");

        assertThat(client.getResult().pop())
                .isEqualTo(10 / 3f, Offset.offset(0.00001));
    }

    @Test
    public void shoud_evaluate_divide_float_complex() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("5.5 7.3 + 6.4 /");

        assertThat(client.getResult().pop())
                .isEqualTo(2, Offset.offset(0.00001));
    }

    @Test
    public void should_evaluate_positive_number_as_absolute() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("17 ABS");

        assertThat(client.getResult().pop()).isEqualTo(17);
    }

    @Test
    public void should_evaluate_negative_number_as_absolute() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("-17 ABS");

        assertThat(client.getResult().pop()).isEqualTo(17);    }

    @Test
    public void should_evaluate_complex_negative_result_as_absolute() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 3 5 + + 19 - ABS");

        assertThat(client.getResult().pop()).isEqualTo(9);
    }

    @Test
    public void should_evaluate_swapping_substract_operation() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 9 SWAP -");

        assertThat(client.getResult().pop()).isEqualTo(7);
    }

    @Test
    public void should_evaluate_swapping_divide_operation() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("2 8 SWAP /");

        assertThat(client.getResult().pop()).isEqualTo(4);
    }

    @Test
    public void should_evaluate_drop_operation() {
        ClientConsumer client = new ClientConsumer(BUS);
        BUS.subscribe(EndOfCalcul.MESSAGE_TYPE, client);
        client.sendExpression("-2 3 4 DROP +");

        assertThat(client.getResult().pop()).isEqualTo(1);
    }

    @Test
    public void should_evaluate_time_operation_on_numbers() {
//        assertThat(evaluate("1 3 TIME + +")).isEqualTo(3);
    }


}
