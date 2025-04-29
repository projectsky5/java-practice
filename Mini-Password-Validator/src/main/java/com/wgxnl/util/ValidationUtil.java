package com.wgxnl.util;

import java.util.function.Predicate;

public class ValidationUtil {
    private static final Predicate<String> LESS_THAN_EIGHT_SYMBOL = s -> s.length() >= 8;
    private static final Predicate<String> CONTAINS_UPPER_CHAR = s -> s.matches(".*[A-Z].*");
    private static final Predicate<String> CONTAINS_ONE_NUMBER = s -> s.matches(".*\\d.*");
    private static final Predicate<String> CONTAINS_SPECIAL_SYMBOL = s -> s.matches(".*[^a-zA-Z0-9].*");

    public static final Predicate<String> isValid = LESS_THAN_EIGHT_SYMBOL
            .and(CONTAINS_UPPER_CHAR)
            .and(CONTAINS_ONE_NUMBER)
            .and(CONTAINS_SPECIAL_SYMBOL);

}
