package com.solvd.library.models;

import com.solvd.library.exceptions.BookNotAvailableException;
import com.solvd.library.interfaces.IBorrowable;
import com.solvd.library.interfaces.IManageable;
import com.solvd.library.interfaces.ISearchable;
import com.solvd.library.models.BookStatus;

import java.util.Objects;

public class Book extends LibraryItem implements IBorrowable, IManageable, ISearchable {
private String isbn;
private BookStatus status;
private int timesBorrowed;

public Book(String title, String isbn, boolean available) {
    super(title, isbn);
    this.isbn = isbn;
    this.status = available ? BookStatus.AVAILABLE : BookStatus.BORROWED;
    this.timesBorrowed = 0;
    }

    public String getTitle() {
        return getName();
    }

    public void setTitle(String title) {
        setName(title);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
        setId(isbn);
    }

public boolean isAvailable() {
    return status == BookStatus.AVAILABLE;
}

public void setAvailable(boolean available) {
    this.status = available ? BookStatus.AVAILABLE : BookStatus.BORROWED;
}

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public void setTimesBorrowed(int timesBorrowed) {
        this.timesBorrowed = timesBorrowed;
    }

// Marks the book as borrowed if it is available
public void borrowBook() throws BookNotAvailableException {
    if (status == BookStatus.AVAILABLE) {
        status = BookStatus.BORROWED;
        timesBorrowed++;
    } else {
        throw new BookNotAvailableException("Book '" + getName() + "' is not available for borrowing.");
    }
}

// Marks the book as returned
public void returnBook() {
    status = BookStatus.AVAILABLE;
}

    @Override
    public void borrowItem() {
        try {
            borrowBook();
        } catch (BookNotAvailableException e) {
            // Handle unchecked or just rethrow as runtime for the interface
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnItem() {
        returnBook();
    }

    @Override
    public String getManagementType() {
        return "Book";
    }

    @Override
    public boolean matchesSearch(String query) {
        return getName().toLowerCase().contains(query.toLowerCase()) || 
               isbn.toLowerCase().contains(query.toLowerCase());
    }

@Override
public String generateShortReport() {
    return "Book: " + getName() + " (" + status + ")";
}

@Override
public String toString() {
    return "Book{title='" + getName() + "', isbn='" + isbn + "', status=" + status + "}";
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
