package org.example;

import org.example.model.UserModel;
import org.example.service.ATMService;

import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserModel user = new UserModel("Alex");
        ATMService atmService = new ATMService(user, sc);
        atmService.initStartBalance();
//        Тут должны быть boolean running, switch, case, где последний делает running = false и тд, не хочу на это тратить время, также преобразование double в int для юзер отображения
        atmService.checkBalance();
        atmService.topUpBalance();
        atmService.withdrawBalance();
    }
}
