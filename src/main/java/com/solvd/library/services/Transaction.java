package com.solvd.library.services;

public abstract class Transaction extends AbstractRecord {

    protected Transaction(String name) {
        super(name);
    }

    @Override
    public abstract String toString();
}
