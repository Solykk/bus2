package com.exceptions;

/**
 * @author Dmitriy Lyashenko
 */

public class DuplicateCityStationException extends RuntimeException {
    public DuplicateCityStationException(String message) {
        super(message);
    }
}
