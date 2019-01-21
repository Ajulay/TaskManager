package com.ajulay.exception.unchecked;

public class LoginExistsException extends NullPointerException {

    public LoginExistsException() {
        super("Login already existed.");
    }
}
