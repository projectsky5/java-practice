package com.wgxnl.util;

public class FilterUtil {
    private static final FilterUtil INSTANCE = new FilterUtil();

    private FilterUtil() {
    }

    public static FilterUtil getInstance() {
        return INSTANCE;
    }



    public String[] splitString(String str) {
        return str
                .trim()
                .replaceAll("\\s+", "")
                .replaceAll(",", " ")
                .split(" ");
    }
}
