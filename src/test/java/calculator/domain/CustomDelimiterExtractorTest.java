package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CustomDelimiterExtractorTest {

    private final CustomDelimiterExtractor extractor = CustomDelimiterExtractor.getInstance();

    @Nested
    @DisplayName("성공 케이스")
    public class Success {

        @ParameterizedTest
        @ValueSource(strings = {"//;\\n1,2,3", "//;\\n"})
        @DisplayName("커스텀 구분자를 사용하면 true를 반환한다.")
        public void trueReturnTest(String value) {

            assertThat(extractor.hasCustomDelimiterFormat(value)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,3//;\\n", "/;\\n1,2,3"})
        @DisplayName("올바른 커스텀 구분자 형식이 아니면 false를 반환한다.")
        public void falseReturnTest(String value) {

            assertThat(extractor.hasCustomDelimiterFormat(value)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({
                "'//;\\n1,2,3', ';'",
                "'//!@#\\n1,2','!@#'",
                "'//:D\\n1,2,3',':D'",
                "'///;;\\\\n','/;;\\'"
        })
        @DisplayName("커스텀 구분자를 추출하여 반환한다.")
        public void customDelimiterExtractingTest(String value, String expected) {

            assertThat(extractor.extractCustomDelimiter(value)).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({
                "'//;\\n1,2,3', '1,2,3'",
                "'//!@#\\n1,2','1,2'",
                "'//:D\\n1,2,3','1,2,3'",
                "'///;;\\\\n',''"
        })
        @DisplayName("커스텀 구분자를 제외한 표현식을 추출하여 반환한다.")
        public void expressionExtractingTest(String value, String expected) {

            assertThat(extractor.extractExpression(value)).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    public class Fail {

        @Test
        @DisplayName("접두사가 없는 문자열에서 커스텀 구분자를 추출하면 예외가 발생한다.")
        public void customDelimiterPrefixTest() {

            //given
            String userInput = "/;\\n1,2,3";

            //when & then
            assertThatThrownBy(() -> extractor.extractCustomDelimiter(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.CUSTOM_DELIMITER_PREFIX_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("접미사가 없는 문자열에서 표현식을 추출하면 예외가 발생한다.")
        public void customDelimiterSuffixTest() {

            //given
            String userInput = "/;\n1,2,3";

            //when & then
            assertThatThrownBy(() -> extractor.extractCustomDelimiter(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.CUSTOM_DELIMITER_SUFFIX_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("접미사가 없는 문자열에서 표현식을 추출하면 예외가 발생한다.")
        public void expressionSuffixTest() {

            //given
            String userInput = "//;\n1,2,3";

            //when & then
            assertThatThrownBy(() -> extractor.extractExpression(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ExceptionMessage.CUSTOM_DELIMITER_SUFFIX_NOT_FOUND.getMessage());
        }
    }
}
