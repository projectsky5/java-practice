package org.example.threads;

import org.example.service.MessageService;

public class FirstThread extends Thread {
    @Override
    public void run() {
        new MessageService(Thread.currentThread().getName()).sendMessage();
    }
}
