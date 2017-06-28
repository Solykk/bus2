package com.exceptions;

/**
 * Created by burbulet on 4/24/17.
 */
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message) {
        super(message);
    }
}
