package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private final List<Number> numbers;

    private Numbers(List<Number> numbers) {

        this.numbers = new ArrayList<>(numbers);
    }

    public static Numbers of(String[] userInputSplit) {

        List<Number> numbers = new ArrayList<>();

        for (String singleWord : userInputSplit) {

            numbers.add(Number.of(singleWord));
        }

        return new Numbers(numbers);
    }

    public int addAll() {

        int result = 0;

        for (Number number : numbers) {

            result += number.getValue();
        }

        return result;
    }
}
