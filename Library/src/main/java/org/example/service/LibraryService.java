package org.example.service;

import org.example.interfaces.Rentable;
import org.example.model.Author;
import org.example.model.Reader;
import org.example.model.books.Book;
import org.example.util.Initializer;

import java.util.List;

public class LibraryService {

    private static final LibraryService INSTANCE = new LibraryService();

    private LibraryService() {}

    public static LibraryService getInstance() {
        return INSTANCE;
    }

    private static final  Initializer initializer = Initializer.getInstance();

    private static final List<Book> books = initializer.getBooks();

    public List<Book> getAvailableBooks(){
        return books.stream()
                .filter(Rentable::isAvailable)
                .toList();
    }

    public void rentBook(Book book){
        book.rent();
    }

    public void returnBook(Book book){
        book.returnBack();
    }

    public List<Book> getBooksByAuthor(Author author){
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();
    }


}
