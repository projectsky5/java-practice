package com.wgxnl.service;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class FilteringService {

    private static final FilteringService INSTANCE = new FilteringService();

    private FilteringService() {
    }

    public static FilteringService getInstance() {
        return INSTANCE;
    }

    public void filterPopular(Map<String, Integer> map, int count){
        System.out.printf("\nТоп-%d популярных слов:\n", count);
        map.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .limit(count)
                .forEach(System.out::println);
    }

    public void filterNotPopular(Map<String, Integer> map, int count){
        System.out.printf("\nТоп-%d редких слов (встречаются 1 раз):", count);
        map.entrySet()
                .stream()
                .filter(value -> value.getValue() == 1)
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .limit(count + 1)
                .forEach(value -> System.out.println(value.getKey()));

    }

    public void showUniqueCount(Set<String> set){
        System.out.printf("Кол-во уникальных слов: %d\n", set.size());
    }
}
