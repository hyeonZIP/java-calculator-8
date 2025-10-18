package calculator.controller;

import calculator.view.OutputView;

public class CalculatorController {

    private final OutputView outputView;

    public CalculatorController() {

        this.outputView = new OutputView();
    }

    public void run() {

        outputView.printIntroMessage();
    }
}
