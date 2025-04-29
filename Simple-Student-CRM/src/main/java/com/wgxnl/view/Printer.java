package com.wgxnl.view;

public class Printer {

    private static final Printer INSTANCE = new Printer();

    private Printer() {}

    public static Printer getInstance() {
        return INSTANCE;
    }

    public void printMenu(){
        System.out.println("""
                Меню:
                1. Добавить студента
                2. Добавить оценку студенту
                3. Посмотреть средний балл студента
                4. Найти лучшего студента
                5. Найти худшего студента
                6. Показать всех студентов с оценками
                7. Выход
                """);
    }

}
