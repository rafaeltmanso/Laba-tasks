package com.solvd.library.services;

import com.solvd.library.models.BaseEntity;

public abstract class AbstractRecord extends BaseEntity {

    protected AbstractRecord(String name) {
        super(name);
    }

    @Override
    public abstract String toString();
}
