package com.exceptions;

/**
 * Created by burbulet on 4/27/17.
 */
public class LocaleNotFoundException extends RuntimeException {
    public LocaleNotFoundException(String message) {
        super(message);
    }
}
