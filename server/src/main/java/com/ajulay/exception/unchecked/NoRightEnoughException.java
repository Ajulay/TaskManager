package com.ajulay.exception.unchecked;

public class NoRightEnoughException extends NullPointerException {
    public NoRightEnoughException() {
        super("You have no enough rights to do it.");
    }

}
