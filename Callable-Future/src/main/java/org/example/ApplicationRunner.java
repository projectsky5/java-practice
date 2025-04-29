package org.example;

import java.util.Random;
import java.util.concurrent.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try{
            runSimpleGetExample(executorService);
            runExceptionHandleExample(executorService);
            runIsDoneExample(executorService);
            runCancelExample(executorService);
            executorService.shutdown();
            if(executorService.awaitTermination(60, TimeUnit.SECONDS)){
                System.out.println("Program terminated");
            } else {
                System.out.println("Program did not terminate");
            }
        } catch (ExecutionException | InterruptedException e){
            System.err.println("error");
        }

    }

    private static void runCancelExample(ExecutorService executorService) throws ExecutionException, InterruptedException {
        System.out.println("runCancelExample");
        Callable<String> task = () -> {
            System.out.println("Task is started");
            Thread.sleep(5000);
            return "Task is completed";
        };
        Future<String> future = executorService.submit(task);
        Thread.sleep(1000);
        future.cancel(true);

        if (future.isCancelled()) {
            System.out.println("Success! Task is cancelled");
        } else {
            System.out.println("Failure! Task is not cancelled");
        }

    }

    private static void runExceptionHandleExample(ExecutorService executorService){
        System.out.println("runExceptionHandleExample");
        Callable<Integer> task = () -> {
            return 10 / new Random().nextInt(3);
        };
        Future<Integer> future = executorService.submit(task);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Деление на ноль!");
        }
    }

    private static void runIsDoneExample(ExecutorService executorService) throws ExecutionException, InterruptedException {
        System.out.println("runIsDoneExample");
        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "done";
        };
        Future<String> future = executorService.submit(task);
        boolean before = future.isDone();
        String s = future.get();
        boolean after = future.isDone();
        if(before != after) {
            System.out.println("Success! Task was not done before get and done after");
        }
    }

    private static void runSimpleGetExample(ExecutorService executorService) throws ExecutionException, InterruptedException {
        System.out.println("runSimpleGetExample");
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += i;
            }
            return sum;
        };
        Future<Integer> future = executorService.submit(task);
        System.out.println("Success! There is the sum: " + future.get());

    }
}
