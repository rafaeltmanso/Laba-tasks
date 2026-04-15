package com.solvd.library.interfaces;

@FunctionalInterface
public interface ITransformer<T, R> {
    R transform(T input);
}
