package com.wgxnl.service;

import java.util.Map;
import java.util.Optional;

public class TemparatureService {

    private static final TemparatureService INSTANCE = new TemparatureService();

    private TemparatureService() {
    }

    public static TemparatureService getInstance() {
        return INSTANCE;
    }

    public static double getAverageTemperature(Map<Integer, Integer> map){
        double average = map.entrySet().stream()
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        return average;

    }
    public static Integer getMaxTemperature(Map<Integer, Integer> map){
        Optional<Map.Entry<Integer, Integer>> max = map.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if(max.isPresent()){
            return max.get().getValue();
        }

        return 0;
    }
    public static Integer getMinTemperature(Map<Integer, Integer> map){
        Optional<Map.Entry<Integer, Integer>> min = map.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        if(min.isPresent()){
            return min.get().getValue();
        }

        return 0;

    }

}
