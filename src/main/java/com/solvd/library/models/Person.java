package com.solvd.library.models;

public abstract class Person extends BaseEntity {

    protected Person(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Person{name='" + getName() + "'}";
    }
}
