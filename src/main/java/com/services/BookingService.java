package com.services;

import com.domain.request.BookTicketsRequest;
import com.exceptions.BookingFailedException;

/**
 * Created by burbulet on 4/24/17.
 */
public interface BookingService {
    String book(BookTicketsRequest request) throws BookingFailedException;
}
