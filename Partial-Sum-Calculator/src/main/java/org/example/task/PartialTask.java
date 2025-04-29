package org.example.task;

import java.util.concurrent.Callable;

public class PartialTask implements Callable<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    public PartialTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    private int partialSum(){
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }

    @Override
    public Integer call() throws Exception {
        return partialSum();
    }
}
