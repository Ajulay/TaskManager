package com.ajulay.endpoint.transport;


public abstract class Result {

    private boolean success;

    public Result(boolean data) {
        this.success = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
