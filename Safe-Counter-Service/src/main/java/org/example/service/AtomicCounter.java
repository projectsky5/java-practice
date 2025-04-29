package org.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger();
    private final ExecutorService executor;

    public AtomicCounter(ExecutorService executor) {
        this.executor = executor;
    }

    public void doTask(){
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
        }
    }

    public int getCounter() {
        return counter.get();
    }
}
