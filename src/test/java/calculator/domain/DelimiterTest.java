package calculator.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DelimiterTest {

    @Nested
    @DisplayName("실패 케이스")
    public class Fail {

        @Test
        @DisplayName("구분자가 null이면 에외가 발생한다")
        public void nullDelimiterTest() {

            assertThatThrownBy(() -> Delimiter.of(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("null");
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "    "})
        @DisplayName("구분자로 공백이 입력되면 예외가 발생한다.")
        public void blankDelimiterExceptionTest(String value) {

            assertThatThrownBy(() -> Delimiter.of(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("공백");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "1,", ",1", "1,1", ",1,", "@@@@@@1@@@@@,", "@11"})
        @DisplayName("구분자에 숫자가 포함되어 있으면 예외가 발생한다.")
        public void numberDelimiterExceptionTest(String value) {

            assertThatThrownBy(() -> Delimiter.of(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("숫자");
        }
    }
}
