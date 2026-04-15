package com.solvd.library.utils;

import com.solvd.library.interfaces.IPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository<T> {
    private List<T> items = new ArrayList<>();

    public void save(T item) {
        items.add(item);
    }

    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    public int count() {
        return items.size();
    }

    public List<T> findBy(IPredicate<T> predicate) {
        return items.stream()
                .filter(predicate::test)
                .collect(Collectors.toList());
    }

    public List<T> transformAll(java.util.function.Function<T, T> transformer) {
        return items.stream()
                .map(transformer)
                .collect(Collectors.toList());
    }
}
