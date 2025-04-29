package com.wgxnl.view;

import com.wgxnl.util.StringUtil;
import com.wgxnl.service.TemparatureService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {

    private static final StringUtil stringUtil = StringUtil.getInstance();
    private static final Scanner sc = new Scanner(System.in);
    private static Map<Integer, Integer> tempMap;

    private static void showAsciiGraph(){
        String dot = "*";
        System.out.println("ASCII-график:");
        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()){
            System.out.printf("День %d: ", entry.getKey());
            System.out.print(dot.repeat(Math.abs(entry.getValue())));
            System.out.printf(" %d°C\n", entry.getValue());
        }
    }

    private static void showData(){
        double avg = TemparatureService.getAverageTemperature(tempMap);

        showAverageTemperature(avg);
        showMinTemperature();
        showMaxTemperature();
        showDaysTempLessThanAverage(avg);
        showDaysTempMoreThanAverage(avg);
        showAsciiGraph();
    }

    private static void showMaxTemperature(){
        System.out.printf("Максимальная температура: %d°C\n", TemparatureService.getMaxTemperature(tempMap));
    }

    private static void showMinTemperature(){
        System.out.printf("Минимальная температура: %d°C\n", TemparatureService.getMinTemperature(tempMap));
    }

    private static void showAverageTemperature(double avg){
        System.out.printf("Средняя температура: %.1f°C\n", avg);
    }

    private static void initialize(){
        System.out.print("Введите значения температуры через запятую: ");
        tempMap = stringUtil.getTemperatureList(sc.nextLine());
    }

    private static void showDaysTempLessThanAverage(double avg){
        List<Integer> list = tempMap.entrySet().stream()
                .filter(entry -> entry.getValue() < avg)
                .map(Map.Entry::getKey)
                .toList();

        System.out.print("Температура ниже средней была в дни: ");
        for (Integer value : list) {
            if(!(value == list.getLast())){
                System.out.print(value + ", ");
            } else {
                System.out.println(value);
            }
        }

    }

    private static void showDaysTempMoreThanAverage(double avg){
        List<Integer> list = tempMap.entrySet().stream()
                .filter(entry -> entry.getValue() > avg)
                .map(Map.Entry::getKey)
                .toList();

        System.out.print("Температура выше средней была в дни: ");
        for (Integer value : list) {
            if(!(value == list.getLast())){
                System.out.print(value + ", ");
            } else {
                System.out.println(value);
            }
        }
    }

    public static void run(){
        initialize();
        showData();
    }

}
