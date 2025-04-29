package com.wgxnl.view;

import com.wgxnl.entity.Box;
import com.wgxnl.service.BoxService;
import com.wgxnl.util.Initialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {

    private static final Initialize initialize = Initialize.getInstance();
    private static final BoxService boxService = BoxService.getInstance();

    private static void printBoxesSortedByWeight(List<Box> boxList){
        List<Box> boxes = boxService.sortBoxesByWeight(boxList);
        Map<Integer, Box> map = new HashMap<>();
        for (int i = 0; i < boxes.size(); i++) {
            map.put(i+1, boxes.get(i));
        }
        System.out.println("Сортировка по весу: ");
        for (Map.Entry<Integer, Box> entry : map.entrySet()) {
            System.out.printf("Box %d -> вес: %.1f кг, объем: %.1f л\n", entry.getKey(), entry.getValue().getWeight(), entry.getValue().getVolume());
        }
    }
    private static void printBoxesSortedByVolume(List<Box> boxList){
        List<Box> boxes = boxService.sortBoxesByVolume(boxList);
        Map<Integer, Box> map = new HashMap<>();
        for (int i = 0; i < boxes.size(); i++) {
            map.put(i+1, boxes.get(i));
        }
        System.out.println("\nСортировка по объему: ");
        for (Map.Entry<Integer, Box> entry : map.entrySet()) {
            System.out.printf("Box %d -> вес: %.1f кг, объем: %.1f л\n", entry.getKey(), entry.getValue().getWeight(), entry.getValue().getVolume());
        }
    }
    private static void printBoxesSortedCombined(List<Box> boxList){
        List<Box> boxes = boxService.sortBoxesCombined(boxList);
        Map<Integer, Box> map = new HashMap<>();
        for (int i = 0; i < boxes.size(); i++) {
            map.put(i + 1, boxes.get(i));
        }
        System.out.println("\nСортировка комбинированная : ");
        for (Map.Entry<Integer, Box> entry : map.entrySet()) {
            System.out.printf("Box %d -> вес: %.1f кг, объем: %.1f л\n", entry.getKey(), entry.getValue().getWeight(), entry.getValue().getVolume());
        }
    }
    private static void printSortedBoxes(List<Box> boxList){
        printBoxesSortedByWeight(boxList);
        printBoxesSortedByVolume(boxList);
        printBoxesSortedCombined(boxList);
    }
    private static void printGroupedBoxes(List<Box> boxList){
        System.out.println("\nСгруппированные коробки:");
        Map<String, List<Box>> stringListMap = boxService.groupingBoxesByWeight(boxList);
        for (Map.Entry<String, List<Box>> entry : stringListMap.entrySet()) {
            switch(entry.getKey()){
                case "Легкие":
                    System.out.println("\nКатегория: Легкие (вес < 2кг");
                    entry.getValue().forEach(System.out::println);
                    break;
                case "Средние":
                    System.out.println("\nКатегория: Средние (2-5кг)");
                    entry.getValue().forEach(System.out::println);
                    break;
                case "Тяжелые":
                    System.out.println("\nКатегория: Тяжелые (вес > 5кг)");
                    entry.getValue().forEach(System.out::println);

                    break;
            }
        }
    }

    public static void run(){
        List<Box> boxList = initialize.initialize();

        printSortedBoxes(boxList);
        printGroupedBoxes(boxList);
    }
}
