package com.ajulay.exception.checked;

public class NoSuchAssignerException extends Exception {

    public NoSuchAssignerException() {
        super("no such executor");
    }

    public NoSuchAssignerException(String message) {
        super(message);
    }

}
