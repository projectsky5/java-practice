package service;

public class ArrayService {

    public int sum(int[] array){
        int sum = 0;
        if(arrayCheck(array)){
            for (int i = 0; i < array.length; i++) {
                sum += array[i];
            }
        }
        return sum;
    }

    public int average(int[] array){
        int average = 0;
        if(arrayCheck(array)){
            average = sum(array) / array.length;
        }
        return average;
    }

    public int max(int[] array){
        int max = Integer.MIN_VALUE;
        if(arrayCheck(array)){
            for (int i = 0; i < array.length; i++) {
                max = array[i] > max ? array[i] : max;
            }
        }
        return max;
    }

    public int maxWithMath(int[] array){
        int max = Integer.MIN_VALUE;
        if(arrayCheck(array)){
            for (int i = 0; i < array.length; i++) {
                max = Math.max(max, array[i]);
            }
        }
        return max;
    }

    public int min(int[] array){
        int min = Integer.MAX_VALUE;
        if(arrayCheck(array)){
            for (int i = 0; i < array.length; i++) {
                min = array[i] < min ? array[i] : min;
            }
        }
        return min;
    }

    public int minWithMath(int[] array){
        int min = Integer.MAX_VALUE;
        if(arrayCheck(array)){
            for (int i = 0; i < array.length; i++) {
                min = Math.min(min, array[i]);
            }
        }
        return min;
    }

    private boolean arrayCheck(int[] array){
        return array != null && array.length != 0;
    }


}
