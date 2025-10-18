package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final OutputView outputView;
    private final InputView inputView;
    private final CalculatorService calculatorService;

    public CalculatorController() {

        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.calculatorService = new CalculatorService();
    }

    public void run() {

        // 인트로 메시지 출력
        outputView.printIntroMessage();

        // 사용자 입력 받기
        String userInput = inputView.getUserInput();

        // 덧셈 결과 받기
        int result = calculatorService.getResult(userInput);

        // 덧셈 결과 출력
        outputView.printAddResult(result);
    }
}
