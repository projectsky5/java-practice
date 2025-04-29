package org.example.service;

import java.util.concurrent.ExecutorService;

public class SyncCounter {
    private volatile int counter = 0;
    private final ExecutorService executorService;

    public SyncCounter(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void doTask() {
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                    for (int j = 0; j < 1000; j++) {
                        synchronized (this) {
                            counter++;
                        }
                    }
            });
        }
    }

    public int getCounter(){
        return counter;
    }


}
