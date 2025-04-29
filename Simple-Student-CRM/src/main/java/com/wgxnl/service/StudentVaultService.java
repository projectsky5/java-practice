package com.wgxnl.service;

import java.util.*;

public class StudentVaultService {

    private static final StudentVaultService INSTANCE = new StudentVaultService();

    private StudentVaultService() {}

    public static StudentVaultService getInstance() {
        return INSTANCE;
    }

    private static final Map<String, List<Integer>> studentMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);


    public void addStudent() {
        System.out.print("Введите имя студента: ");
        String name = sc.nextLine();

        if (!studentMap.containsKey(name)) {
            studentMap.put(name, new ArrayList<Integer>());
        } else {
            System.err.printf("Студент с именем %s уже существует\n", name);
        }
    }


    public void addGrade() throws NumberFormatException {
        System.out.print("Введите имя студента и его оценку: ");
        String input = sc.nextLine();
        String[] sanitizeString = sanitizeString(input);
        String name = sanitizeString[0];
        int grade = Integer.parseInt(sanitizeString[1]);

        if(studentMap.containsKey(name)) {
            studentMap.get(name)
                    .add(grade);
        } else {
            System.err.printf("Ошибка! Студента по имени %s не существует\n", name);
        }
    }

    public Map<String, List<Integer>> getStudentMap() {
        return studentMap;
    }

    public void showAllStudents() {

        if (studentMap.isEmpty()) {
            System.err.println("В базе нет ни одного студента");
            return;
        }

        for (Map.Entry<String, List<Integer>> entry : studentMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private String[] sanitizeString(String input) {
        return input.trim()
                .replaceAll("\\s+", " ")
                .split(" ");
    }
}
