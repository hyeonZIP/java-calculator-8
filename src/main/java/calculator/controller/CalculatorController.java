package calculator.controller;

import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final OutputView outputView;
    private final InputView inputView;

    public CalculatorController() {

        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {

        outputView.printIntroMessage();
        String userInput = inputView.getUserInput();
    }
}
