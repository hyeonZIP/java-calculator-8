package calculator.domain;

public class Delimiter {

    private final String value;

    private Delimiter(String value) {

        validateDelimiterFormat(value);

        this.value = value;
    }

    public static Delimiter of(String value) {

        return new Delimiter(value);
    }

    private void validateDelimiterFormat(String value) {

        validateDelimiterIsNotNull(value);

        validateDelimiterIsNotBlank(value);

        validateDelimiterIsNotDigit(value);
    }

    private void validateDelimiterIsNotBlank(String value) {

        if (value.isBlank()) {

            throw new IllegalArgumentException("[ERROR] 구분자는 공백일 수 없습니다.");
        }
    }

    private void validateDelimiterIsNotDigit(String value) {

        for (char singleWord : value.toCharArray()) {

            if (Character.isDigit(singleWord)) {

                throw new IllegalArgumentException("[ERROR] 구분자는 숫자일 수 없습니다.");
            }
        }
    }

    private void validateDelimiterIsNotNull(String value) {

        if (value == null) {

            throw new IllegalArgumentException("[ERROR] 구분자는 null 일 수 없습니다.");
        }
    }
}
