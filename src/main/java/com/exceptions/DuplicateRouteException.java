package com.exceptions;

/**
 * @author Dmitriy Lyashenko
 */

public class DuplicateRouteException extends RuntimeException {
    public DuplicateRouteException(String message) {
        super(message);
    }
}
