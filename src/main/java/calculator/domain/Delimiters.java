package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class Delimiters {
    
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private static final List<Delimiter> DEFAULT_DELIMITERS = List.of(
            Delimiter.of(COMMA),
            Delimiter.of(COLON)
    );

    private final List<Delimiter> delimiters;

    private Delimiters(List<Delimiter> delimiters) {

        this.delimiters = new ArrayList<>(delimiters);
    }

    public static Delimiters ofDefault() {

        return new Delimiters(DEFAULT_DELIMITERS);
    }

    public static Delimiters ofCustom(String customDelimiter) {

        List<Delimiter> customDelimiters = new ArrayList<>(DEFAULT_DELIMITERS);

        customDelimiters.addFirst(Delimiter.of(customDelimiter));

        return new Delimiters(customDelimiters);
    }
}
