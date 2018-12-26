package com.ajulay.exception;

public class NoSuchProjectException extends Exception {

    public NoSuchProjectException() {
        super("No such project");
    }

    public NoSuchProjectException(String message) {
        super(message);
    }
}
