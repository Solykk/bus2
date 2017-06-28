package com.exceptions;

/**
 * Created by burbulet on 4/24/17.
 */
public class BookingFailedException extends RuntimeException {
    public BookingFailedException(String message) {
        super(message);
    }
}
