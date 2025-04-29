package configuration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayInitializer {

    public static int[] buildArray(Scanner scanner) throws NumberFormatException, InputMismatchException {
        System.out.println("Введите размер массива: ");
        if(scanner.hasNextInt()) {
            int length = scanner.nextInt();
            int[] array = new int[length];
            System.out.println("Введите элементы массива: ");
            int[] arrayNew = fillArray(array, scanner);
            scanner.nextLine();
            return arrayNew;
        }
        else{
            throw new NumberFormatException();
        }
    }

    private static int[] fillArray(int[] array, Scanner scanner) throws InputMismatchException {
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("\nМассив создан!\n");
        return array;
    }

}
