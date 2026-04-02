package com.solvd.library.models;

import com.solvd.library.services.Loan;
import com.solvd.library.services.Notification;
import com.solvd.library.services.Reservation;

import java.util.*;

public class Library extends BaseEntity {
    private String address;
    private List<Book> books; // 1. List
    private Set<Member> members; // 2. Set
    private Map<String, Loan> activeLoans; // 3. Map
    private Queue<Reservation> reservationQueue; // 4. Queue
    private PriorityQueue<String> urgentNotes; // 5. PriorityQueue

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

    @Override
    public String toString() {
        return "Library{name='" + getName() + "', address='" + address + 
               "', booksCount=" + books.size() + 
               ", membersCount=" + members.size() + "}";
    }
}
