package calculator.domain;

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

        int startIndex = userInput.indexOf(CUSTOM_DELIMITER_PREFIX) + CUSTOM_DELIMITER_PREFIX.length();
        int endIndex = userInput.indexOf(CUSTOM_DELIMITER_SUFFIX);

        return userInput.substring(startIndex, endIndex);
    }

    public boolean hasCustomDelimiterFormat(String userInput) {

        return userInput.startsWith(CUSTOM_DELIMITER_PREFIX) && userInput.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    public String extractExpression(String userInput) {

        int endIndex = userInput.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length();

        return userInput.substring(endIndex);
    }
}
