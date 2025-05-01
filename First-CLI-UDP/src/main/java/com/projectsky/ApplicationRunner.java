package com.projectsky;

public class ApplicationRunner {
    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        client.sendMessage();
    }
}
