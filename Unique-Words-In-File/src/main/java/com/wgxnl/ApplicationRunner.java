package com.wgxnl;

import com.wgxnl.service.FileAnalyzerService;

import java.util.Scanner;

public class ApplicationRunner {

    static FileAnalyzerService fileAnalyzerService = FileAnalyzerService.getInstance();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите размер топа: ");
        int count = sc.nextInt();
        sc.nextLine();
        fileAnalyzerService.analyzeFile("input.txt", count);
    }

}
