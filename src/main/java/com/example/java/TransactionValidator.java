package com.example.java;

public final class TransactionValidator {

    private TransactionValidator() {
    }

    public static Result<Transaction> validate(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            return Result.failure("amount must be greater than 0");
        } else if (transaction.getStatus().isEmpty()) {
            return Result.failure("status must be present");
        } else {
            return Result.success(transaction);
        }
    }
}