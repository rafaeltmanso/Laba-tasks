package com.solvd.library.utils;

import java.util.ArrayList;
import java.util.List;

public class CustomList<T> {
    private List<T> list = new ArrayList<>();

    public void add(T element) {
        list.add(element);
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return "CustomList{" + list + "}";
    }
}
