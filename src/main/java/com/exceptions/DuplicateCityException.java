package com.exceptions;

/**
 * Created by burbulet on 4/27/17.
 */
public class DuplicateCityException extends RuntimeException {
    public DuplicateCityException(String message) {
        super(message);
    }
}
