package com.exceptions;

/**
 * Created by burbulet on 4/24/17.
 */
public class MailSendingFailedException extends RuntimeException {
    public MailSendingFailedException(String message) {
        super(message);
    }
}
