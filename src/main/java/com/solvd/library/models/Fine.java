package com.solvd.library.models;

import com.solvd.library.services.AbstractRecord;
import java.util.Objects;

public class Fine extends AbstractRecord {
    private double amount;
    private boolean paid;

    public Fine(double amount, boolean paid) {
        super("Fine: " + amount);
        this.amount = amount;
        this.paid = paid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Fine{amount=" + amount + ", paid=" + paid + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fine fine = (Fine) o;
        return Double.compare(fine.amount, amount) == 0 && paid == fine.paid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, paid);
    }
}
