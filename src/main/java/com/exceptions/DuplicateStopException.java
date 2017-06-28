package com.exceptions;

/**
 * @author Dmitriy Lyashenko
 */

public class DuplicateStopException extends RuntimeException {
    public DuplicateStopException(String message) {
        super(message);
    }
}
