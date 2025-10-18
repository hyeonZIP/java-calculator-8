package calculator.service;

import calculator.domain.Numbers;
import calculator.domain.ParsedInput;

public class CalculatorService {

    public int getResult(String userInput) {

        ParsedInput parsedInput = ParsedInput.of(userInput);

        String[] userInputSplit = parsedInput.split();

        Numbers parsedUserInput = Numbers.of(userInputSplit);

        return parsedUserInput.addAll();
    }
}
