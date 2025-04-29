package org.example.model.books;

import org.example.interfaces.Rentable;
import org.example.model.Author;

import java.util.Objects;

public abstract class Book implements Rentable {
    String title;
    Author author;
    BookStatus status;

    public Book(String title, Author author, BookStatus status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    abstract void printInfo();

    public Author getAuthor() {
        return author;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, status);
    }
}
