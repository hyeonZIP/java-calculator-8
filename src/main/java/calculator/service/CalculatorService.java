package calculator.service;

import calculator.domain.Numbers;
import calculator.domain.ParsedInput;
import java.math.BigInteger;

public class CalculatorService {

    public BigInteger getResult(String userInput) {

        // 사용자 입력에 따른 사용할 구분자 목록과 표현식으로 파싱
        ParsedInput parsedInput = ParsedInput.of(userInput);

        // 공백 입력 또는 커스텀 구분자를 제외한 표현식이 공백이면 조기 반환
        if (parsedInput.hasExpression()) {

            // 파싱된 표현식과 구분자로 분리
            String[] userInputSplit = parsedInput.split();

            // 분리된 표현식을 양수로 파싱
            Numbers parsedUserInput = Numbers.of(userInputSplit);

            // 파싱된 양수들의 합산 결과 반환
            return parsedUserInput.addAll();
        }

        return BigInteger.ZERO;
    }
}
