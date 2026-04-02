package com.solvd.library.services;

import com.solvd.library.models.Book;
import com.solvd.library.models.Member;

public class Reservation extends Transaction {
    private Book book;
    private Member member;
    private boolean active;

    public Reservation(Book book, Member member, boolean active) {
        super("Reservation for " + book.getTitle());
        this.book = book;
        this.member = member;
        this.active = active;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Reservation{book=" + book.getTitle() + ", member=" + member.getName() + ", active=" + active + "}";
    }
}
