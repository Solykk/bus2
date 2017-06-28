package com.exceptions;

/**
 * @author Dmitriy Lyashenko
 */

public class DuplicateCoordinateException extends RuntimeException{
    public DuplicateCoordinateException(String message) {
        super(message);
    }
}
