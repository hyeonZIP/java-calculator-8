package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DelimitersTest {

    @Nested
    @DisplayName("커스텀 구분자")
    public class CustomDelimiter {

        @Test
        @DisplayName("커스텀 구분자와 기본 구분자로 문자열을 분리한다.")
        public void customDelimiterTest() {

            //given
            String customDelimiter = "$";
            String expression = "1,2$3";
            Delimiters delimiters = Delimiters.ofCustom(customDelimiter);

            //when
            String[] result = delimiters.split(expression);

            //then
            assertThat(result).isEqualTo(new String[]{"1", "2", "3"});
        }
    }

    @Nested
    @DisplayName("기본 구분자")
    public class DefaultDelimiter {

        @Test
        @DisplayName("기본 구분자로 문자열을 분리한다.")
        public void defaultDelimiterTest() {

            //given
            String expression = "1,2:3";
            Delimiters delimiters = Delimiters.ofDefault();

            //when
            String[] result = delimiters.split(expression);

            //then
            assertThat(result).isEqualTo(new String[]{"1", "2", "3"});
        }
    }
}
