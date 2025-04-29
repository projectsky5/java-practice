package org.example;

import org.example.service.MessageService;
import org.example.threads.FirstThread;
import org.example.threads.SecondThread;

public class ApplicationRunner {
    public static void main(String[] args) {
        FirstThread t1 = new FirstThread();
        Thread t2 = new Thread(new SecondThread());
        Thread t3 = new Thread( () -> {
            new MessageService(Thread.currentThread().getName()).sendMessage();
        });

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted");
        }
    }
}
