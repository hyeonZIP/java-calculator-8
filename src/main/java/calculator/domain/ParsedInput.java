package calculator.domain;

import java.math.BigInteger;
import org.junit.platform.commons.util.StringUtils;

public class ParsedInput {

    private final String expression;
    private final Delimiters delimiters;

    private ParsedInput(String expression, Delimiters delimiters) {

        this.expression = expression;
        this.delimiters = delimiters;
    }

    public static ParsedInput of(String userInput) {

        CustomDelimiterExtractor extractor = CustomDelimiterExtractor.getInstance();

        if (extractor.hasCustomDelimiterFormat(userInput)) {

            // 기본 구분자와 커스텀 구분자가 포함된 객체 생성
            return createForCustomDelimiter(userInput, extractor);
        }

        // 기본 구분자로만 이루어진 객체 생성
        return createDefault(userInput);
    }

    public BigInteger calculate() {

        // 공백 입력 또는 커스텀 구분자를 제외한 표현식이 공백이면 조기 반환
        if (isExpressionBlank()) {

            return BigInteger.ZERO;
        }

        // 파싱된 표현식과 구분자로 분리
        String[] userInputSplit = delimiters.split(expression);

        // 분리된 표현식을 양수로 파싱
        Numbers parsedUserInput = Numbers.of(userInputSplit);

        // 파싱된 양수들의 합산 결과 반환
        return parsedUserInput.addAll();
    }

    private boolean isExpressionBlank() {

        return StringUtils.isBlank(expression);
    }

    private static ParsedInput createDefault(String userInput) {

        Delimiters delimiters = Delimiters.ofDefault();

        return new ParsedInput(userInput, delimiters);
    }

    private static ParsedInput createForCustomDelimiter(String userInput, CustomDelimiterExtractor extractor) {

        // 사용자 입력에서 커스텀 구분자 추출
        String customDelimiter = extractor.extractCustomDelimiter(userInput);

        // 커스텀 구분자가 포함된 구분자 리스트 반환
        Delimiters delimiters = Delimiters.ofCustom(customDelimiter);

        // 사용자 입력에서 커스텀 구분자 부분을 제외하여 표현식 추출
        String expression = extractor.extractExpression(userInput);

        return new ParsedInput(expression, delimiters);
    }
}
