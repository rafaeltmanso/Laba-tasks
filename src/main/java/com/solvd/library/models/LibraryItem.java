package com.solvd.library.models;

public abstract class LibraryItem extends BaseEntity {
    private String id;

    protected LibraryItem(String name, String id) {
        super(name);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public abstract String toString();
}
