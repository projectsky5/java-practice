package org.example;

import org.example.task.PartialTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();

        int partLenght = array.length / 10;
        for (int i = 0; i < 10; i++) {
            int start = i * partLenght;
            int end = (i == 9) ? array.length : start + partLenght;
            futures.add(executorService.submit(new PartialTask(array, start, end)));
        }
        executorService.shutdown();
        try {
            if(executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES)){
                printSum(futures);
            } else {
                System.out.println("No tasks found");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printSum(List<Future<Integer>> futures) throws ExecutionException, InterruptedException {
        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        System.out.println(sum);
    }
}
