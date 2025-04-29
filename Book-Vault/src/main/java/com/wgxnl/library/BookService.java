package com.wgxnl.library;

import com.wgxnl.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private static final List<Book> books = new ArrayList<>();

    public List<String> searchByAuthor(String author) {
        return books.stream()
                .filter( book -> book.getAuthor().equals(author) )
                .map(Book::getTitle)
                .toList();
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        books.add(new Book(title, author, year));
    }

    public void printAllBooks() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }


}
