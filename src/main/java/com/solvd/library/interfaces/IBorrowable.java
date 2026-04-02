package com.solvd.library.interfaces;

public interface IBorrowable {
    void borrowItem();
    void returnItem();
    boolean isAvailable();
}
