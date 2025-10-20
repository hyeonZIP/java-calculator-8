package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    @DisplayName("모든 숫자를 더하여 반환한다.")
    public void addAllTest() {

        //given
        String[] userInputSplit = new String[]{"1", "2", "3"};
        ExpressionTokens expressionTokens = ExpressionTokens.of(userInputSplit);
        Numbers numbers = Numbers.of(expressionTokens.toNumber());

        //when
        BigInteger result = numbers.addAll();

        //then
        assertThat(result).isEqualTo(6);
    }
}
