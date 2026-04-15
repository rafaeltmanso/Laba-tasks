package com.solvd.library.interfaces;

@FunctionalInterface
public interface IPredicate<T> {
    boolean test(T item);
}
