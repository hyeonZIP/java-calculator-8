package calculator.domain;

public class Number {

    private static final int ZERO = 0;

    private final int value;

    private Number(String value) {

        validateNumber(value);

        this.value = Integer.parseInt(value);
    }

    public static Number of(String userInputSplit) {

        return new Number(userInputSplit);
    }

    private static void validateNumber(String value) {

        validateNumberIsNotNull(value);

        validateNumberIsNotBlank(value);

        validateNumberIsNotDigit(value);

        validateNumberIsNotPositive(value);
    }

    private static void validateNumberIsNotBlank(String value) {

        if (value.isBlank()) {

            throw new IllegalArgumentException("[ERROR] 파싱할 숫자가 공백입니다.");
        }
    }

    private static void validateNumberIsNotPositive(String value) {

        if (Integer.parseInt(value) <= ZERO) {

            throw new IllegalArgumentException("[ERROR] 파싱할 숫자가 양수가 아닙니다.");
        }
    }

    private static void validateNumberIsNotNull(String value) {

        if (value == null) {

            throw new IllegalArgumentException("[ERROR] 파싱할 숫자가 null 입니다.");
        }
    }

    private static void validateNumberIsNotDigit(String value) {

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 문자는 정수로 파싱할 수 없습니다.");
        }
    }
}
