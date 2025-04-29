package org.example.model;

public class UserModel {
    private final String name;
    private double balance;

    public UserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
