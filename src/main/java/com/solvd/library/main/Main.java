package com.solvd.library.main;

import com.solvd.library.exceptions.BookNotAvailableException;
import com.solvd.library.exceptions.InvalidLoanException;
import com.solvd.library.exceptions.MemberNotEligibleException;
import com.solvd.library.interfaces.IBorrowable;
import com.solvd.library.interfaces.IManageable;
import com.solvd.library.interfaces.IReportable;
import com.solvd.library.interfaces.ISearchable;
import com.solvd.library.models.*;
import com.solvd.library.services.Loan;
import com.solvd.library.services.Notification;
import com.solvd.library.services.Reservation;
import com.solvd.library.utils.CustomList;
import com.solvd.library.utils.OperationResult;
import com.solvd.library.utils.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Use Generic Repository
        Repository<Book> bookRepository = new Repository<>();
        Repository<Member> memberRepository = new Repository<>();

        // Create library
        Library library = new Library("City Library", "12 Main Street");

        // Create librarian
        Librarian librarian = new Librarian("Mr. Smith", 101);

        // Create author and category
        Author author = new Author("J.K. Rowling", "British");
        Category category = new Category("Fantasy", "F1");

        // Create books and add to library (using List)
        Book book1 = new Book("Harry Potter", "ISBN001", true);
        Book book2 = new Book("Java Basics", "ISBN002", false);
        library.addBook(book1);
        library.addBook(book2);
        bookRepository.save(book1);
        bookRepository.save(book2);

        // Create members and add to library (using Set)
        Member member1 = new Member("Alice", 1, "alice@example.com");
        Member member2 = new Member("Bob", 2, "bob@example.com");
        library.addMember(member1);
        library.addMember(member2);
        memberRepository.save(member1);
        memberRepository.save(member2);

        logger.info("Welcome to " + library.getName());
        logger.info("Library address: " + library.getAddress());
        logger.info("Librarian: " + librarian.getName());
        logger.info("Author: " + author.getName());
        logger.info("Category: " + category.getCategoryName());
        logger.info("Total books in repository: " + bookRepository.count());
        logger.info("");

        // Check if the first book is available
        logger.info("Checking availability of: " + book1.getTitle());
        if (librarian.checkAvailability(book1)) {
            logger.info("Book is available. Borrowing now...");

            try {
                // Borrow the book
                book1.borrowBook();

                // Create a loan (using Map in Library)
                Loan loan1 = new Loan(book1, member1, "2026-03-16", "2026-03-30");
                library.recordLoan(book1.getIsbn(), loan1);

                logger.info(member1.getName() + " borrowed " + loan1.getBook().getTitle());
                logger.info("Borrow date: " + loan1.getBorrowDate());
                logger.info("Due date: " + loan1.getDueDate());
            } catch (BookNotAvailableException | InvalidLoanException e) {
                logger.error("Failed to complete loan: " + e.getMessage());
            }
        } else {
            logger.info("Book is not available.");
        }

        logger.info("");

        // Check second book and reserve it if unavailable (using Queue in Library)
        logger.info("Checking availability of: " + book2.getTitle());
        if (!librarian.checkAvailability(book2)) {
            logger.info("Book is currently unavailable.");

            Reservation reservation = new Reservation(book2, member2, true);
            library.addReservation(reservation);
            logger.info(member2.getName() + " reserved the book: " + reservation.getBook().getTitle());
        }

        logger.info("");

        // Demonstration of PriorityQueue
        library.addUrgentNote("A-Security check at 6 PM");
        library.addUrgentNote("C-Restock coffee");
        library.addUrgentNote("B-Update system");
        logger.info("Next urgent note: " + library.getUrgentNotes().poll());

        logger.info("");

        // Demonstration of CustomList (Generic Class)
        CustomList<String> tags = new CustomList<>();
        tags.add("New Arrival");
        tags.add("Best Seller");
        logger.info("Book Tags: " + tags.getAll());

        // Demonstration of OperationResult (Generic Class)
        OperationResult<Book> result = OperationResult.success(book1);
        if (result.isSuccess()) {
            logger.info("Operation successful for: " + result.getData().getTitle());
        }

        logger.info("");

        // Polymorphism demonstration
        logger.info("Polymorphism Demonstration:");
        Person[] people = new Person[3];
        people[0] = librarian;
        people[1] = member1;
        people[2] = author;

        for (Person p : people) {
            logger.info("Person info: " + p.toString());
        }

        logger.info("");

        // Apply a simple fine for late return
        Fine fine = new Fine(5.0, false);
        logger.info("Late return fine for " + member1.getName() + ": $" + fine.getAmount());

        logger.info("");

        // Show book popularity
        logger.info("Book popularity:");
        logger.info(book1.getTitle() + " was borrowed " + book1.getTimesBorrowed() + " time(s).");
        logger.info(book2.getTitle() + " was borrowed " + book2.getTimesBorrowed() + " time(s).");

        logger.info("");

        // IBorrowable demonstration
        IBorrowable borrowableBook = book1;
        logger.info("Checking availability via IBorrowable: " + borrowableBook.isAvailable());
        if (borrowableBook.isAvailable()) {
            borrowableBook.borrowItem();
            logger.info("Book borrowed using IBorrowable interface.");
        }

        logger.info("");

        // ISearchable demonstration
        ISearchable searchableMember = member1;
        String query = "Alice";
        logger.info("Searching for '" + query + "' in member: " + searchableMember.matchesSearch(query));

        logger.info("");

        // IManageable demonstration
        IManageable manageableBook = book2;
        logger.info("Management Type: " + manageableBook.getManagementType() + ", ID: " + manageableBook.getId());

        logger.info("");

        // IReportable demonstration
        logger.info("Short Reports:");
        IReportable[] reportables = {book1, book2, member1, librarian};
        for (IReportable r : reportables) {
            logger.info(r.generateShortReport());
        }

        logger.info("");

        // INotifiable demonstration in Notification
        Notification librarianNotification = new Notification("System update at 10 PM", librarian);
        librarianNotification.sendNotification();

        logger.info("");

        // Return reserved book and notify member
        book2.returnBook();

        Notification notification = new Notification(
                "Your reserved book '" + book2.getTitle() + "' is now available.",
                member2
        );
        notification.sendNotification();

        // Demonstrate MemberNotEligibleException (Runtime)
        try {
            checkMemberEligibility(member1);
        } catch (MemberNotEligibleException e) {
            logger.warn("Member eligibility check failed: " + e.getMessage());
        }
    }

    private static void checkMemberEligibility(Member member) {
        // Logic example: if member has unpaid fines (we don't have a list of fines in Member yet, so just dummy check)
        if (member.getName().equals("Alice")) {
             // Let's pretend Alice has too many fines
             throw new MemberNotEligibleException("Member " + member.getName() + " is not eligible due to outstanding fines.");
        }
    }
}
