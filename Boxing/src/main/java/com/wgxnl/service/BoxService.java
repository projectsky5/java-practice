package com.wgxnl.service;

import com.wgxnl.entity.Box;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxService {

    private static final BoxService instance = new BoxService();

    private BoxService() {
    }

    public static BoxService getInstance() {
        return instance;
    }

    public List<Box> sortBoxesByWeight(List<Box> boxList) {
        return boxList.stream()
                .sorted(Comparator.comparing(Box::getWeight).reversed())
                .toList();
    }

    public List<Box> sortBoxesByVolume(List<Box> boxList) {
        return boxList.stream()
                .sorted(Comparator.comparing(Box::getVolume).reversed())
                .toList();
    }

    public List<Box> sortBoxesCombined(List<Box> boxList) {
        return boxList.stream()
                .sorted(Comparator.comparing(Box::getWeight).thenComparing(Box::getVolume).reversed())
                .toList();
    }

    public Map<String, List<Box>> groupingBoxesByWeight(List<Box> boxList) {
        Map<String, List<Box>> map = sortBoxesByWeight(boxList).stream()
                .collect(Collectors.groupingBy(box -> {
                    if(box.getWeight() < 2) return "Легкие";
                    if(box.getWeight() > 2 && box.getWeight() <= 5) return "Средние";
                    return "Тяжелые";
                }));

        return map;
    }

}
