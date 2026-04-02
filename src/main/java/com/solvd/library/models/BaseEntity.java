package com.solvd.library.models;

import com.solvd.library.interfaces.IReportable;

public abstract class BaseEntity implements IReportable {
    private String name;

    protected BaseEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String generateShortReport() {
        return "Entity: " + name;
    }

    @Override
    public abstract String toString();
}
