package calculator.exception;

public enum ExceptionMessage {

    //Delimiter
    DELIMITER_CONTAINS_DIGIT("[ERROR] 구분자는 숫자를 포함할 수 없습니다."),
    DELIMITER_IS_BLANK("[ERROR] 구분자는 비어있거나 null일 수 없습니다."),

    //Number
    NUMBER_IS_BLANK("[ERROR] 숫자 문자열은 비어있거나 null일 수 없습니다."),
    NUMBER_MUST_BE_POSITIVE("[ERROR] 숫자는 양수만 가능합니다."),
    NUMBER_IS_INVALID_FORMAT("[ERROR] 숫자 형식이 올바르지 않습니다."),

    //CustomDelimiterExtractor
    CUSTOM_DELIMITER_SUFFIX_NOT_FOUND("[ERROR] 커스텀 구분자 접미사를 찾을 수 없습니다."),
    CUSTOM_DELIMITER_PREFIX_NOT_FOUND("[ERROR] 커스텀 구분자 접두사를 찾을 수 없습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}
