package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ExceptionMessage;
import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringCalculatorTest {

    @Nested
    @DisplayName("성공 케이스")
    public class Success {

        @Test
        @DisplayName("기본 구분자만 사용된 문자열을 분리하여 반환한다.")
        public void defaultDelimiterTest() {

            //given
            String userInput = "1:2,3";
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when
            BigInteger result = stringCalculator.calculate();

            //then
            assertThat(result).isEqualTo(BigInteger.valueOf(6));
        }

        @Test
        @DisplayName("커스텀 구분자가 사용된 문자열을 분리하여 반환한다.")
        public void customDelimiterTest() {

            //given
            String userInput = "//#######\\n1#######2#######3";
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when
            BigInteger result = stringCalculator.calculate();

            //then
            assertThat(result).isEqualTo(BigInteger.valueOf(6));
        }

        @Test
        @DisplayName("공백이 입력되면 0을 반환한다.")
        public void emptyInputTest() {

            //given
            String userInput = "";
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when
            BigInteger result = stringCalculator.calculate();

            //then
            assertThat(result).isEqualTo(BigInteger.ZERO);
        }

        @Test
        @DisplayName("long 자료형 표현 범위를 벗어난 입력값도 결과를 출력한다")
        public void overflowTest() {

            //given
            String userInput = String.format("%s,%s:%s", Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when
            BigInteger result = stringCalculator.calculate();

            //then
            assertThat(result).isEqualTo(BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(3)));
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    public class Fail{

        @Test
        @DisplayName("커스텀 구분자에 숫자가 포함되면 예외를 발생시킨다.")
        public void customDelimiterDigitTest() {

            //given
            String userInput = "//,2,\\n1,2,3";

            //when & then
            assertThatThrownBy(()->StringCalculator.of(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.DELIMITER_CONTAINS_DIGIT.getMessage());
        }

        @Test
        @DisplayName("커스텀 구분자가 공백이면 예외를 발생시킨다.")
        public void customDelimiterBlankTest() {

            //given
            String userInput = "//\\n1,2,3";

            //when & then
            assertThatThrownBy(()->StringCalculator.of(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.DELIMITER_IS_BLANK.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,0","1,2,-3"})
        @DisplayName("덧셈할 숫자가 0이거나 음수면 예외를 발생시킨다.")
        public void numberZeroTest(String userInput) {

            //given
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when & then
            assertThatThrownBy(stringCalculator::calculate)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.NUMBER_MUST_BE_POSITIVE.getMessage());
        }

        @Test
        @DisplayName("숫자로 파싱할 수 없는 문자면 예외를 발생시킨다.")
        public void numberParsingTest() {

            //given
            String userInput = "일,이:삼";
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when & then
            assertThatThrownBy(stringCalculator::calculate)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.NUMBER_IS_INVALID_FORMAT.getMessage());
        }

        @Test
        @DisplayName("숫자가 공백이면 예외를 발생시킨다.")
        public void numberBlankTest() {

            //given
            String userInput = "1,2:";
            StringCalculator stringCalculator = StringCalculator.of(userInput);

            //when & then
            assertThatThrownBy(stringCalculator::calculate)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.NUMBER_IS_BLANK.getMessage());
        }
    }
}
