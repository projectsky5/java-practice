package com.wgxnl.service;

import java.util.*;

public class StudentKpiService {

    private static final StudentVaultService studentVaultService = StudentVaultService.getInstance();
    private static final Scanner sc = new Scanner(System.in);

    private static final StudentKpiService INSTANCE = new StudentKpiService();

    private StudentKpiService() {
    }

    public static StudentKpiService getInstance() {
        return INSTANCE;
    }

    public void showAverageGrade() {
        System.out.print("Введите имя студента: ");
        String name = sc.nextLine();
        Map<String, List<Integer>> studentMap = studentVaultService.getStudentMap();

        if(!studentMap.containsKey(name)) {
            System.err.printf("Студента с именем %s не существует", name);
            return;
        }

        double average = studentMap.get(name)
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        System.out.printf("Средний балл студента: %s = %d\n", name, Math.round(average));

    }

    public void showBestStudent() {
        Map<String, List<Integer>> studentMap = studentVaultService.getStudentMap();
        Map<String, Double> studentsWithAvgGrades = getStudentsWithAvgGrades(studentMap);

        Optional<Map.Entry<String, Double>> bestStudentOpt = studentsWithAvgGrades.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if(bestStudentOpt.isEmpty()){
            System.err.println("В базе нет ни одного студента");
            return;
        }

        Map.Entry<String, Double> bestStudent = bestStudentOpt.get();

        //Округляю в бОльшую сторону, как и принято в Российском образовании
        System.out.printf("Лучший студент: %s, Средний балл = %d\n", bestStudent.getKey(), Math.round(bestStudent.getValue()));
    }

    public void showWorstStudent() {
        Map<String, List<Integer>> studentMap = studentVaultService.getStudentMap();
        Map<String, Double> studentsWithAvgGrades = getStudentsWithAvgGrades(studentMap);

        Optional<Map.Entry<String, Double>> worstStudentOpt = studentsWithAvgGrades.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        if(worstStudentOpt.isEmpty()){
            System.err.println("В базе нет ни одного студента");
            return;
        }

        Map.Entry<String, Double> worstStudent = worstStudentOpt.get();

        //Округляю в бОльшую сторону, как и принято в Российском образовании
        System.out.printf("Худший студент: %s, Средний балл = %d\n", worstStudent.getKey(), Math.round(worstStudent.getValue()));
    }

    private Map<String, Double> getStudentsWithAvgGrades(Map<String, List<Integer>> studentMap) {
        Map<String, Double> studentsWithAverageGrades = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : studentMap.entrySet()) {
            String studentName = entry.getKey();
            double avgGrade = entry.getValue().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0);
            studentsWithAverageGrades.put(studentName, avgGrade);
        }
        return studentsWithAverageGrades;
    }


}
