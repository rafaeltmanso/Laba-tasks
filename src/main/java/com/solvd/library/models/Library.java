package com.solvd.library.models;

import com.solvd.library.interfaces.IPredicate;
import com.solvd.library.services.Loan;
import com.solvd.library.services.Notification;
import com.solvd.library.services.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class Library extends BaseEntity {
    private String address;
    private List<Book> books;
    private Set<Member> members;
    private Map<String, Loan> activeLoans;
    private Queue<Reservation> reservationQueue;
    private PriorityQueue<String> urgentNotes;

    public Library(String name, String address) {
        super(name);
        this.address = address;
        this.books = new ArrayList<>();
        this.members = new HashSet<>();
        this.activeLoans = new HashMap<>();
        this.reservationQueue = new LinkedList<>();
        this.urgentNotes = new PriorityQueue<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Map<String, Loan> getActiveLoans() {
        return activeLoans;
    }

    public void recordLoan(String isbn, Loan loan) {
        activeLoans.put(isbn, loan);
    }

    public Queue<Reservation> getReservationQueue() {
        return reservationQueue;
    }

    public void addReservation(Reservation reservation) {
        reservationQueue.offer(reservation);
    }

    public PriorityQueue<String> getUrgentNotes() {
        return urgentNotes;
    }

    public void addUrgentNote(String note) {
        urgentNotes.add(note);
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Member> getActiveMembers() {
        return members.stream()
                .filter(member -> member.getStatus() == MemberStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public List<String> getAllBookTitles() {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public List<Book> findBooks(IPredicate<Book> predicate) {
        return books.stream()
                .filter(predicate::test)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Library{name='" + getName() + "', address='" + address +
               "', booksCount=" + books.size() +
               ", membersCount=" + members.size() + "}";
    }
}
