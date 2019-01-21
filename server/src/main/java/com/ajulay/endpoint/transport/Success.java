package com.ajulay.endpoint.transport;

public class Success extends Result {

    public Success() {
        super(true);
    }

    public Success(boolean data) {
        super(data);
    }
}
