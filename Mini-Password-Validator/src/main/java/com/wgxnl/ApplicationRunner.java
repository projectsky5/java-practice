package com.wgxnl;

import com.wgxnl.util.FilterUtil;
import com.wgxnl.util.ValidationUtil;

import java.util.*;

public class ApplicationRunner {

    private static final FilterUtil filterUtil = FilterUtil.getInstance();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите список паролей: ");
        System.out.println("Валидные пароли: " + Arrays.stream(filterUtil.splitString(sc.nextLine()))
                        .filter(ValidationUtil.isValid)
                                .toList());

    }
}
