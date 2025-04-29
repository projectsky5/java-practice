package com.wgxnl;

import com.wgxnl.service.StudentKpiService;
import com.wgxnl.service.StudentVaultService;
import com.wgxnl.view.Printer;

import java.util.Scanner;

public class ApplicationRunner {

    private static final StudentVaultService studentVaultService = StudentVaultService.getInstance();
    private static final StudentKpiService studentKpiService = StudentKpiService.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Printer printer = Printer.getInstance();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printer.printMenu();
            String command = scanner.nextLine();

            switch(command){
                case "1":
                    studentVaultService.addStudent();
                    break;
                case "2":
                    try{
                        studentVaultService.addGrade();
                        break;
                    } catch (NumberFormatException e){
                        System.err.println("Неверный ввод. Оценка должна быть целым числом");
                        break;
                    }
                case "3":
                    studentKpiService.showAverageGrade();
                    break;
                case "4":
                    studentKpiService.showBestStudent();
                    break;
                case "5":
                    studentKpiService.showWorstStudent();
                    break;
                case "6":
                    studentVaultService.showAllStudents();
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.err.println("Неверная команда. Повторите ввод");
            }
        }
    }
}
