package org.example.model.books;

import org.example.model.Author;
import org.example.model.Reader;

import java.util.Objects;

public class PrintedBook extends Book {
    int pageCount;

    public PrintedBook(String title, Author author, BookStatus status, int pageCount) {
        super(title, author, status);
        this.pageCount = pageCount;
    }


    @Override
    void printInfo() {
        System.out.println("Печатная книга: " + title + ", " + author + ", " + pageCount);
    }

    @Override
    public void rent() {
        if (this.status.equals(BookStatus.AVAILABLE)) {
            this.status = BookStatus.RENTED;
        } else {
            System.out.println("Книги нет в наличии");
        }
    }

    @Override
    public void returnBack() {
        this.status = BookStatus.AVAILABLE;
    }

    @Override
    public boolean isAvailable() {
        return this.status.equals(BookStatus.AVAILABLE);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PrintedBook that = (PrintedBook) o;
        return pageCount == that.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pageCount);
    }

    @Override
    public String toString() {
        return "PrintedBook{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", pageCount=" + pageCount +
                ", status=" + status +
                '}';
    }
}
