package com.exceptions;

/**
 * Created by burbulet on 5/1/17.
 */
public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(String message) {
        super(message);
    }
}
