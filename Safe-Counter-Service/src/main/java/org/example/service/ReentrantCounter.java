package org.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantCounter {
    ReentrantLock lock = new ReentrantLock();
    private volatile int counter = 0;
    ExecutorService executorService;

    public ReentrantCounter(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void doTask(){
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    lock.lock();
                    try{
                        counter++;
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
    }

    public int getCounter() {
        return counter;
    }
}
