package calculator.service;

import calculator.domain.ParsedInput;

public class CalculatorService {

    public int getResult(String userInput) {

        int result = 0;

        ParsedInput parsedInput = ParsedInput.of(userInput);

        return result;
    }
}
