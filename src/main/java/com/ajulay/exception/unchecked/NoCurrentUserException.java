package com.ajulay.exception.unchecked;

public class NoCurrentUserException extends NullPointerException {

    public NoCurrentUserException() {
        super("No current user");
    }

}
