package calculator.service;

import calculator.domain.Numbers;
import calculator.domain.ParsedInput;

public class CalculatorService {

    public int getResult(String userInput) {

        int result = 0;

        ParsedInput parsedInput = ParsedInput.of(userInput);

        String[] userInputSplit = parsedInput.split();

        Numbers parsedUserInput = Numbers.of(userInputSplit);

        return result;
    }
}
