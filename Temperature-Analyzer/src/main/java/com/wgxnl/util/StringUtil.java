package com.wgxnl.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {

    private static final StringUtil INSTANCE = new StringUtil();

    private StringUtil() {}

    public static StringUtil getInstance() {
        return INSTANCE;
    }

    public Map<Integer, Integer> getTemperatureList(String str) {
        Map<Integer, Integer> map = new HashMap<>();
        try{
            List<Integer> list = Arrays.stream(str.trim()
                            .replaceAll("\\s+", "")
                            .replaceAll(",", " ")
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            for (int i = 0; i < list.size(); i++) {
                map.put(i + 1, list.get(i));
            }
        } catch (NumberFormatException e){
            System.err.println("Неверный ввод");
        }

        return map;
    }
}
