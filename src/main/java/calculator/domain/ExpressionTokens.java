package calculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExpressionTokens {

    private final String[] values;

    private ExpressionTokens(String[] values) {

        this.values = values;
    }

    public static ExpressionTokens of(String[] values) {

        return new ExpressionTokens(values);
    }

    public List<Number> toNumber() {

        List<Number> numbers = new ArrayList<>();

        for (String value : values) {

            numbers.add(Number.of(value));
        }

        return numbers;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {

            return false;
        }

        ExpressionTokens that = (ExpressionTokens) obj;

        return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {

        return Objects.hash((Object) values);
    }
}
