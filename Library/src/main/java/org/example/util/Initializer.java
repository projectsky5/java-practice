package org.example.util;

import org.example.model.Author;
import org.example.model.books.Book;
import org.example.model.books.BookStatus;
import org.example.model.books.EBook;
import org.example.model.books.PrintedBook;

import java.util.List;

public class Initializer {

    private static final Initializer INSTANCE = new Initializer();

    private Initializer() {
    }

    public static Initializer getInstance() {
        return INSTANCE;
    }

    public final Author tolkien = new Author("J.R.R. Tolkien", 1892);
    public final Author rowling = new Author("J.K. Rowling", 1965);
    public final Author orwell = new Author("George Orwell", 1903);
    public final Author asimov = new Author("Isaac Asimov", 1920);
    public final Author christie = new Author("Agatha Christie", 1890);
    public final Author king = new Author("Stephen King", 1947);
    public final Author martin = new Author("George R.R. Martin", 1948);
    public final Author pushkin = new Author("Alexander Pushkin", 1799);
    public final Author dostoevsky = new Author("Fyodor Dostoevsky", 1821);
    public final Author hemingway = new Author("Ernest Hemingway", 1899);

    private List<Book> books = List.of(
            new PrintedBook("The Hobbit", tolkien, BookStatus.AVAILABLE, 310),
            new PrintedBook("Harry Potter and the Philosopher's Stone", rowling, BookStatus.AVAILABLE, 223),
            new EBook("1984", orwell, BookStatus.AVAILABLE, 1.5),
            new PrintedBook("Foundation", asimov, BookStatus.AVAILABLE, 255),
            new EBook("Murder on the Orient Express", christie, BookStatus.AVAILABLE, 0.9),
            new PrintedBook("The Shining", king, BookStatus.AVAILABLE, 447),
            new PrintedBook("A Game of Thrones", martin, BookStatus.AVAILABLE, 694),
            new EBook("Eugene Onegin", pushkin, BookStatus.AVAILABLE, 1.1),
            new PrintedBook("Crime and Punishment", dostoevsky, BookStatus.AVAILABLE, 671),
            new EBook("The Old Man and the Sea", hemingway, BookStatus.AVAILABLE, 0.8)
    );

    public List<Book> getBooks() {
        return books;
    }
}
