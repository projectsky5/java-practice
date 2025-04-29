package com.wgxnl;

import com.wgxnl.entity.Book;
import com.wgxnl.library.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationRunner {

    private static final BookService bookService = new BookService();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter a book count: ");
        int bookCount = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < bookCount; i++) {
            System.out.printf("Enter %d book\n", i + 1);
            bookService.addBook(sc);
        }
        System.out.print("Enter a author that book you wanna find: ");
        String author = sc.nextLine();
        System.out.printf("Books found by author: %s\n", author);
        System.out.println(bookService.searchByAuthor(author));

        System.out.println("All books");
        bookService.printAllBooks();
    }
}
