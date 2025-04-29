package com.wgxnl;

import java.util.*;
import java.util.stream.Stream;

public class ApplicationRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите строку");
        String str = sc.nextLine();

        String[] s = str.trim().split("\\s+");

        System.out.printf("Слов: %d\n", s.length);
        System.out.println("Список: " + Arrays.toString(s));

        System.out.println("Доп задание\n");
        wordCounter(str);
    }

    public static void wordCounter(String str){
        Map<String, Integer> map = new HashMap<>();
        String s = str.replaceAll("\\pP", "");
        String[] split = s.trim().split("\\s+");
        for (String word : split) {
            word = word.toLowerCase();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        map.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .forEach(entry -> {
                    System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
                });


    }
}
