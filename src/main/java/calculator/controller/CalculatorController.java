package calculator.controller;

import calculator.domain.ParsedInput;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.math.BigInteger;

public class CalculatorController {

    private final OutputView outputView;
    private final InputView inputView;

    public CalculatorController() {

        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {

        // 인트로 메시지 출력
        outputView.printIntroMessage();

        // 사용자 입력 받기
        String userInput = inputView.getUserInput();

        // 덧셈 결과 받기
        BigInteger result = ParsedInput.of(userInput).calculate();

        // 덧셈 결과 출력
        outputView.printAddResult(result);
    }
}
