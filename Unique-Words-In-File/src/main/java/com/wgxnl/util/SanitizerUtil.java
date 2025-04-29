package com.wgxnl.util;

public class SanitizerUtil {

    private static final SanitizerUtil INSTANCE = new SanitizerUtil();

    private SanitizerUtil() {}

    public static SanitizerUtil getInstance() {
        return INSTANCE;
    }

    public String[] sanitize(String str){
        return str.trim()
                .replaceAll("\\s+", " ")
                .replaceAll("\\pP", "")
                .toLowerCase()
                .split("\\W+");
    }
}
