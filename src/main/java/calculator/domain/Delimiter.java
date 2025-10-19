package calculator.domain;

import calculator.exception.ExceptionMessage;
import java.util.regex.Pattern;
import org.junit.platform.commons.util.StringUtils;

public class Delimiter {

    private final String value;

    private Delimiter(String value) {

        validateDelimiterFormat(value);

        this.value = value;
    }

    public static Delimiter of(String value) {

        return new Delimiter(value);
    }

    public String getRegexPattern() {

        return Pattern.quote(value);
    }

    private void validateDelimiterFormat(String value) {

        validateDelimiterIsNotBlank(value);

        validateDelimiterIsNotDigit(value);
    }

    private void validateDelimiterIsNotBlank(String value) {

        if (StringUtils.isBlank(value)) {

            throw new IllegalArgumentException(ExceptionMessage.DELIMITER_IS_BLANK.getMessage());
        }
    }

    private void validateDelimiterIsNotDigit(String value) {

        for (char singleWord : value.toCharArray()) {

            if (Character.isDigit(singleWord)) {

                throw new IllegalArgumentException(ExceptionMessage.DELIMITER_CONTAINS_DIGIT.getMessage());
            }
        }
    }
}
