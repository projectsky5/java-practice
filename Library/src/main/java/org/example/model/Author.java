package org.example.model;

import java.util.Objects;

public class Author {
    String name;
    int birthYear;

    public Author(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return birthYear == author.birthYear && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }

    public String getName() {
        return name;
    }
}
