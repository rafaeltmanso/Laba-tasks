package com.solvd.library.services;

import com.solvd.library.exceptions.InvalidLoanException;
import com.solvd.library.models.Book;
import com.solvd.library.models.Member;

public class Loan extends Transaction {
    private Book book;
    private Member member;
    private String borrowDate;
    private String dueDate;

    public Loan(Book book, Member member, String borrowDate, String dueDate) throws InvalidLoanException {
        super("Loan for " + book.getTitle());
        if (book == null || member == null) {
            throw new InvalidLoanException("Book and Member cannot be null for a loan.");
        }
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
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

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Loan{book=" + book.getTitle() + ", member=" + member.getName() + ", due=" + dueDate + "}";
    }
}
