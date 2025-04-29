package org.example.model.books;

import org.example.model.Author;
import org.example.model.Reader;

import java.util.Objects;

public class EBook extends Book {
    double fileSizeMB;

    public EBook(String title, Author author, BookStatus status, double fileSizeMB) {
        super(title, author, status);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    void printInfo() {
        System.out.println("Электронная книга: " + title + ", " + author + ", " + fileSizeMB + "MB");
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
        EBook eBook = (EBook) o;
        return Double.compare(fileSizeMB, eBook.fileSizeMB) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fileSizeMB);
    }

    @Override
    public String toString() {
        return "EBook{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", fileSizeMB=" + fileSizeMB +
                ", status=" + status +
                '}';
    }
}
