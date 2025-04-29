package org.example.interfaces;

import org.example.model.Reader;

public interface Rentable {
    void rent();
    void returnBack();
    boolean isAvailable();
}
