package rpn;

import org.assertj.core.data.Offset;
import org.junit.Test;
import rpn.Exceptions.UnsufficientArgumentException;
import rpn.Exceptions.UnsupportedExpressionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static rpn.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        try {
            assertThat(evaluate("5")).isEqualTo(5);

        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        try{
            assertThat(evaluate("17")).isEqualTo(17);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_evaluate_simple_addition() {
        try {
            assertThat(evaluate("17 5 +")).isEqualTo(22);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        try {
            assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void should_evaluate_more_complex_addition_with_substract() {
        try {
            assertThat(evaluate("2 3 5 + + 9 -")).isEqualTo(1);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void should_evaluate_multiply() {
        try {
            assertThat(evaluate("2 6 *")).isEqualTo(12);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void should_evaluate_multiply_more_complex() {
        try {
            assertThat(evaluate("2 6 4 * *")).isEqualTo(48);

        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void shoud_evaluate_divide(){
        try {
            assertThat(evaluate("6 3 /")).isEqualTo(2);
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shoud_evaluate_divide_float(){
        try {
            assertThat(evaluate("6 4 + 3 /")).isEqualTo(10/3f, Offset.offset(0.00001));
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shoud_evaluate_divide_float_complex(){
        try {
            assertThat(evaluate("5.5 7.3 + 6.4 /")).isEqualTo(2, Offset.offset(0.00001));
        } catch (UnsupportedExpressionException | UnsufficientArgumentException e) {
            e.printStackTrace();
        }
    }
}