package com.example.java;

import java.util.Objects;
import java.util.Optional;

public final class Transaction {

    private final String id;
    private final double amount;
    private final Optional<String> status;

    public Transaction(String id, double amount, Optional<String> status) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.amount = amount;
        this.status = Objects.requireNonNull(status, "status must not be null");
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Optional<String> getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction other)) return false;
        return Double.compare(other.amount, amount) == 0
                && id.equals(other.id)
                && status.equals(other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, status);
    }
}