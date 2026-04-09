package com.example.java;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TransactionValidatorJavaTest {

    @Test
    void shouldReturnSuccessForValidTransaction() {
        Transaction transaction = new Transaction("txn-1", 100.0, Optional.of("COMPLETED"));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isSuccess());
        assertEquals(transaction, result.getValue());
    }

    @Test
    void shouldReturnFailureWhenAmountIsZero() {
        Transaction transaction = new Transaction("txn-2", 0.0, Optional.of("COMPLETED"));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isFailure());
        assertEquals("amount must be greater than 0", result.getError());
    }

    @Test
    void shouldReturnFailureWhenAmountIsNegative() {
        Transaction transaction = new Transaction("txn-3", -10.0, Optional.of("COMPLETED"));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isFailure());
        assertEquals("amount must be greater than 0", result.getError());
    }

    @Test
    void shouldReturnFailureWhenStatusIsMissing() {
        Transaction transaction = new Transaction("txn-4", 100.0, Optional.empty());

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isFailure());
        assertEquals("status must be present", result.getError());
    }

    @Test
    void shouldAcceptUnknownStatusAsLongAsItIsPresent() {
        Transaction transaction = new Transaction("txn-5", 100.0, Optional.of("RANDOM_VALUE"));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isSuccess());
        assertEquals(transaction, result.getValue());
    }

    @Test
    void shouldReturnAmountErrorFirstWhenBothAmountAndStatusAreInvalid() {
        Transaction transaction = new Transaction("txn-6", 0.0, Optional.empty());

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isFailure());
        assertEquals("amount must be greater than 0", result.getError());
    }

    @Test
    void shouldAcceptBlankStatusAsLongAsOptionalIsPresent() {
        Transaction transaction = new Transaction("txn-7", 100.0, Optional.of(""));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isSuccess());
        assertEquals(transaction, result.getValue());
    }

    @Test
    void shouldAcceptWhitespaceStatusAsLongAsOptionalIsPresent() {
        Transaction transaction = new Transaction("txn-8", 100.0, Optional.of("   "));

        Result<Transaction> result = TransactionValidator.validate(transaction);

        assertTrue(result.isSuccess());
        assertEquals(transaction, result.getValue());
    }
}