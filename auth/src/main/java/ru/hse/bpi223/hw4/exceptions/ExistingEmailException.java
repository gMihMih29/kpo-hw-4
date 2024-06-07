package ru.hse.bpi223.hw4.exceptions;

public class ExistingEmailException extends Exception {
    public ExistingEmailException(String message) {
        super(message);
    }
}
