package com.exceptions;

/**
 * Created by burbulet on 4/27/17.
 */
public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String message) {
        super(message);
    }
}
