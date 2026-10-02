package org.example.parkinglot.entity;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
