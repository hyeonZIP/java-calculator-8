package calculator.domain;

public class ParsedInput {

    private final String expression;
    private final Delimiters delimiters;

    private ParsedInput(String expression, Delimiters delimiters) {

        this.expression = expression;
        this.delimiters = delimiters;
    }

    public static ParsedInput of(String userInput) {

        CustomDelimiterExtractor extractor = CustomDelimiterExtractor.of();

        if (extractor.hasCustomDelimiterFormat(userInput)) {

            return createForCustomDelimiter(userInput, extractor);
        }

        return createDefault(userInput);
    }

    public String[] split(){

        return delimiters.split(expression);
    }

    private static ParsedInput createDefault(String userInput) {

        Delimiters delimiters = Delimiters.ofDefault();

        return new ParsedInput(userInput, delimiters);
    }

    private static ParsedInput createForCustomDelimiter(String userInput, CustomDelimiterExtractor extractor) {

        String customDelimiter = extractor.extractCustomDelimiter(userInput);

        Delimiters delimiters = Delimiters.ofCustom(customDelimiter);

        String expression = extractor.extractExpression(userInput);

        return new ParsedInput(expression, delimiters);
    }
}
