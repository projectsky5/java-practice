package org.example.view;

import org.example.model.books.Book;
import org.example.service.LibraryService;
import org.example.util.Initializer;

import java.util.List;
import java.util.Scanner;

public class LibraryView {

    private static final Scanner sc = new Scanner(System.in);
    private static final LibraryService libraryService = LibraryService.getInstance();
    private static final Initializer initializer = Initializer.getInstance();

    public static void run(){
        Book book = initializer.getBooks().getFirst();
        boolean running = true;
        while(running){
            printMenu();
            String command = sc.nextLine();
            switch (command){
                case "1" -> printAllBooks();
                case "2" -> printBooksByAuthor();
                case "3" -> takeBook(book);
                case "4" -> returnBook(book);
                case "5" -> printAvailableBooks();
                case "6" -> running = false;
                default -> System.out.println("Invalid command");
            }
        }
    }

    private static void printAllBooks(){
        initializer.getBooks().forEach(System.out::println);
    }

    private static void printBooksByAuthor(){
        printMenuForBooksByAuthor();
        List<Book> list = null;
        while(list == null){
            list = choiceAuthor();
        }
        list.forEach(System.out::println);
    }

    private static void takeBook(Book book){
        libraryService.rentBook(book);
    }

    private static void returnBook(Book book){
        libraryService.returnBook(book);
    }

    private static List<Book> choiceAuthor() {
        String input = sc.nextLine();
        switch (input) {
            case "1":
                libraryService.getBooksByAuthor(initializer.tolkien);
                break;
            case "2":
                libraryService.getBooksByAuthor(initializer.rowling);
                break;
            case "3":
                libraryService.getBooksByAuthor(initializer.orwell);
                break;
            case "4":
                libraryService.getBooksByAuthor(initializer.asimov);
                break;
            case "5":
                libraryService.getBooksByAuthor(initializer.christie);
                break;
            case "6":
                libraryService.getBooksByAuthor(initializer.king);
                break;
            case "7":
                libraryService.getBooksByAuthor(initializer.martin);
                break;
            case "8":
                libraryService.getBooksByAuthor(initializer.pushkin);
                break;
            case "9":
                libraryService.getBooksByAuthor(initializer.dostoevsky);
                break;
            case "10":
                libraryService.getBooksByAuthor(initializer.hemingway);
                break;
            default:
                System.out.println("Invalid choice");
        }
        return null;
    }

    private static void printAvailableBooks() {
        libraryService.getAvailableBooks().forEach(System.out::println);
    }

    private static void printMenu(){
        System.out.println("""
                1. Показать все книги
                2. Показать книги по автору
                3. Взять книгу в аренду
                4. Вернуть книгу
                5. Показать доступные книги
                6. Выход
                """);
    }

    private static void printMenuForBooksByAuthor(){
        System.out.println("""
                Выберите нужного автора:
                1. Толкиен
                2. Роулинг
                3. Оруэлл
                4. Азимов
                5. Кристи
                6. Кинг
                7. Мартин
                8. Пушкин
                9. Достоевский
                10. Хемингуэй
                """);
    }

}
