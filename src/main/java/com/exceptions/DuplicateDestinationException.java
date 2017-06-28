package com.exceptions;

/**
 * Created by burbulet on 5/1/17.
 */
public class DuplicateDestinationException extends RuntimeException {
    public DuplicateDestinationException(String message) {
        super(message);
    }
}
