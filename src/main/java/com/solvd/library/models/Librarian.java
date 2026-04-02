package com.solvd.library.models;

import com.solvd.library.interfaces.INotifiable;
import com.solvd.library.interfaces.ISearchable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Librarian extends Person implements INotifiable, ISearchable {
    private static final Logger logger = LogManager.getLogger(Librarian.class);
    private int employeeId;

    public Librarian(String name, int employeeId) {
        super(name);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Checks whether the book is available
    public boolean checkAvailability(Book book) {
        return book.isAvailable();
    }

    @Override
    public void receiveNotification(String message) {
        logger.info("[Internal Memo to Librarian " + employeeId + "] " + message);
    }

    @Override
    public boolean matchesSearch(String query) {
        return getName().toLowerCase().contains(query.toLowerCase()) || 
               String.valueOf(employeeId).equals(query);
    }

    @Override
    public String generateShortReport() {
        return "Librarian: " + getName() + " (EID: " + employeeId + ")";
    }

    @Override
    public String toString() {
        return "Librarian{name='" + getName() + "', employeeId=" + employeeId + "}";
    }
}
