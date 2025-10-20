package calculator.domain;

import calculator.exception.ExceptionMessage;

public class CustomDelimiterExtractor {

    private static final CustomDelimiterExtractor SINGLETON_INSTANCE = new CustomDelimiterExtractor();
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    private CustomDelimiterExtractor() {
    }

    public static CustomDelimiterExtractor getInstance() {

        return SINGLETON_INSTANCE;
    }

    public String extractCustomDelimiter(String userInput) {

        validateCustomDelimiterSuffix(userInput);
        validateCustomDelimiterPrefix(userInput);

        int startIndex = userInput.indexOf(CUSTOM_DELIMITER_PREFIX);
        int endIndex = userInput.indexOf(CUSTOM_DELIMITER_SUFFIX);

        return userInput.substring(startIndex + CUSTOM_DELIMITER_PREFIX.length(), endIndex);
    }

    public boolean hasCustomDelimiterFormat(String userInput) {

        return userInput.startsWith(CUSTOM_DELIMITER_PREFIX) && userInput.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    public String extractExpression(String userInput) {

        validateCustomDelimiterSuffix(userInput);

        int endIndex = userInput.indexOf(CUSTOM_DELIMITER_SUFFIX);

        return userInput.substring(endIndex + CUSTOM_DELIMITER_SUFFIX.length());
    }

    private void validateCustomDelimiterPrefix(String userInput) {

        if (!userInput.contains(CUSTOM_DELIMITER_PREFIX)) {

            throw new IllegalArgumentException(ExceptionMessage.CUSTOM_DELIMITER_PREFIX_NOT_FOUND.getMessage());
        }
    }

    private void validateCustomDelimiterSuffix(String userInput) {

        if (!userInput.contains(CUSTOM_DELIMITER_SUFFIX)) {

            throw new IllegalArgumentException(ExceptionMessage.CUSTOM_DELIMITER_SUFFIX_NOT_FOUND.getMessage());
        }
    }
}
