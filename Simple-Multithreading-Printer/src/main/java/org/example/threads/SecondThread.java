package org.example.threads;

import org.example.service.MessageService;

public class SecondThread implements Runnable {
    @Override
    public void run() {
        new MessageService(Thread.currentThread().getName()).sendMessage();
    }
}
