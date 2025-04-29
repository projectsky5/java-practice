package org.example;

import org.example.service.AtomicCounter;
import org.example.service.ReentrantCounter;
import org.example.service.SyncCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Atomic:");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AtomicCounter counter = new AtomicCounter(executorService);
        counter.doTask();
        executorService.shutdown();
        if(executorService.awaitTermination(1, TimeUnit.MINUTES)){
            System.out.println("Final counter: " + counter.getCounter());
        } else {
            System.out.println("Timed out");
        }

        System.out.println("Sync:");
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        SyncCounter counter2 = new SyncCounter(executorService2);
        counter2.doTask();
        executorService2.shutdown();
        if (executorService2.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Final counter: " + counter2.getCounter());
        } else {
            System.out.println("Timed out");
        }

        System.out.println("Reentrant:");
        ExecutorService executorService3 = Executors.newFixedThreadPool(3);
        ReentrantCounter counter3 = new ReentrantCounter(executorService3);
        counter3.doTask();
        executorService3.shutdown();
        if (executorService3.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Final counter: " + counter3.getCounter());
        } else {
            System.out.println("Timed out");
        }

    }
}

