package calculator.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NumberTest {

    @Nested
    @DisplayName("실패 케이스")
    public class Fail {

        @Test
        @DisplayName("null이 입력될 경우 예외가 발생한다.")
        public void nullNumberTest() {

            //given
            String[] userInputSplit = new String[]{"1", "2", null, "4"};

            //when & then
            assertThatThrownBy(() -> Numbers.of(userInputSplit))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("null");
        }

        @Test
        @DisplayName("문자가 입력될 경우 예외가 발생한다.")
        public void notPositiveTest() {

            //given
            String[] userInputSplit = new String[]{"1", "2", "나야 들기름", "4"};

            //when & then
            assertThatThrownBy(() -> Numbers.of(userInputSplit))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("문자");
        }

        @Test
        @DisplayName("공백이 입력될 경우 예외가 발생한다.")
        public void nullBlankTest() {

            //given
            String[] userInputSplit = new String[]{"1", "2", " ", "4"};

            //when & then
            assertThatThrownBy(() -> Numbers.of(userInputSplit))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("공백");
        }

        @Test
        @DisplayName("음수가 입력될 경우 예외가 발생한다.")
        public void notPositiveNumberTest() {

            //given
            String[] userInputSplit = new String[]{"1", "2", "-3", "4"};

            //when & then
            assertThatThrownBy(() -> Numbers.of(userInputSplit))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수");
        }

        @Test
        @DisplayName("0이 입력될 경우 예외가 발생한다.")
        public void zeroNumberTest() {

            //given
            String[] userInputSplit = new String[]{"1", "2", "0", "4"};

            //when & then
            assertThatThrownBy(() -> Numbers.of(userInputSplit))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("양수");
        }
    }
}
