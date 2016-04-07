package com.jayseeofficial.marvel.rest.exception;

/**
 * Created by jon on 05/04/16.
 */
public class TooManyRequestsException extends RuntimeException {

    private String message;

    public TooManyRequestsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
