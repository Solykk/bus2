package com.services;

import com.exceptions.MailSendingFailedException;
import com.domain.request.BookTicketsInternalRequest;

/**
 * Created by burbulet on 4/24/17.
 */
public interface MailService {
    void send(BookTicketsInternalRequest request) throws MailSendingFailedException;
}
