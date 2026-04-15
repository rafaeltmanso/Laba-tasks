package com.solvd.library.interfaces;

import java.util.List;

@FunctionalInterface
public interface ICollectionProcessor<T> {
    List<T> process(List<T> items);
}
