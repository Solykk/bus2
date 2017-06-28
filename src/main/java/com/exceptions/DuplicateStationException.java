package com.exceptions;

/**
 * @author Dmitriy Lyashenko
 */

public class DuplicateStationException extends RuntimeException {
    public DuplicateStationException(String message) {
        super(message);
    }
}
