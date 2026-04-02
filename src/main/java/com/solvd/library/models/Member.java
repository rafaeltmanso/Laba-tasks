package com.solvd.library.models;

import com.solvd.library.interfaces.INotifiable;
import com.solvd.library.interfaces.ISearchable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Member extends Person implements INotifiable, ISearchable {
    private static final Logger logger = LogManager.getLogger(Member.class);
    private int memberId;
    private String email;

    public Member(String name, int memberId, String email) {
        super(name);
        this.memberId = memberId;
        this.email = email;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void receiveNotification(String message) {
        logger.info("[Email Notification to " + email + "] " + message);
    }

    @Override
    public boolean matchesSearch(String query) {
        return getName().toLowerCase().contains(query.toLowerCase()) || 
               email.toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public String generateShortReport() {
        return "Member: " + getName() + " (ID: " + memberId + ")";
    }

    @Override
    public String toString() {
        return "Member{name='" + getName() + "', memberId=" + memberId + ", email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, email);
    }
}
