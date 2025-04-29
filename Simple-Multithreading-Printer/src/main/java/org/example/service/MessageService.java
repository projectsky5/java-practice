package org.example.service;

public class MessageService {
    private final String threadName;

    public MessageService(String threadName) {
        this.threadName = threadName;
    }

    public void sendMessage(){
        for (int i = 0; i < 10; i++) {
            System.out.println(this.threadName);
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
                break;
            }
        }
    }
}
