package calculator.domain;

import calculator.exception.ExceptionMessage;
import java.math.BigInteger;
import org.junit.platform.commons.util.StringUtils;

public class Number {

    private static final BigInteger ZERO = BigInteger.ZERO;

    private final BigInteger value;

    private Number(String value) {

        validateNumber(value);

        this.value = new BigInteger(value);
    }

    public static Number of(String userInputSplit) {

        return new Number(userInputSplit);
    }

    public BigInteger getValue() {

        return value;
    }

    private static void validateNumber(String value) {

        validateNumberIsNotBlank(value);

        validateNumberIsNotDigit(value);

        validateNumberIsNotPositive(value);
    }

    private static void validateNumberIsNotBlank(String value) {

        if (StringUtils.isBlank(value)) {

            throw new IllegalArgumentException(ExceptionMessage.NUMBER_IS_BLANK.getMessage());
        }
    }

    private static void validateNumberIsNotPositive(String value) {

        BigInteger number = new BigInteger(value);

        if (number.compareTo(ZERO) <= 0) {

            throw new IllegalArgumentException(ExceptionMessage.NUMBER_MUST_BE_POSITIVE.getMessage());
        }
    }

    private static void validateNumberIsNotDigit(String value) {

        try {
            new BigInteger(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.NUMBER_IS_INVALID_FORMAT.getMessage());
        }
    }
}
