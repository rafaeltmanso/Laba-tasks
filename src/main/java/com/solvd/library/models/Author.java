package com.solvd.library.models;

public class Author extends Person {
    private String nationality;

    public Author(String name, String nationality) {
        super(name);
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
