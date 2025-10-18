package calculator.view;

public class OutputView {

    private static final String INTRO_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private static final String OUTRO_MESSAGE = "결과 : ";

    public void printIntroMessage() {

        System.out.println(INTRO_MESSAGE);
    }

    public void printAddResult(int result) {

        System.out.println(OUTRO_MESSAGE + result);
    }
}
