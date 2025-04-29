import configuration.ArrayInitializer;
import service.ArrayService;
import view.Printer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationRunner {

    private static final ArrayService arrayService =  new ArrayService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        try{
            int[] array = ArrayInitializer.buildArray(sc);

            while(running){
                Printer.printMenu();
                String input = sc.nextLine();
                switch(input){
                    case "1" -> System.out.printf("Сумма: %d\n", arrayService.sum(array));
                    case "2" -> System.out.printf("Среднее арифметическое : %d\n", arrayService.average(array));
                    case "3" -> System.out.printf("Максимальное значение : %d\n", arrayService.max(array));
                    case "4" -> System.out.printf("Максимальное значение с функцией Math : %d\n", arrayService.maxWithMath(array));
                    case "5" -> System.out.printf("Минимальное значение : %d\n", arrayService.min(array));
                    case "6" -> System.out.printf("Минимальное значение с функцией Math : %d", arrayService.minWithMath(array));
                    case "7" -> running = false;
                    default -> System.out.println("Invalid input");
                }
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println("Неверный формат ввода");
        }
    }
}
