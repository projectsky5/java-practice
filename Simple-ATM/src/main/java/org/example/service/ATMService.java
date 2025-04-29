package org.example.service;

import org.example.model.UserModel;

import java.util.Scanner;

public class ATMService {
    private final Scanner scanner;
    private final UserModel userModel;

    public ATMService(UserModel user, Scanner scanner) {
        this.userModel = user;
        this.scanner = scanner;
    }

    public void initStartBalance() {
        this.userModel.setBalance(1000);
    }
    public void checkBalance() {
        System.out.println(userModel.getBalance());
    }
    public void topUpBalance(){
        this.userModel.setBalance(userModel.getBalance() + this.scanner.nextInt());
    }

    public void withdrawBalance(){
        this.userModel.setBalance(userModel.getBalance() - this.scanner.nextInt());
    }
}
