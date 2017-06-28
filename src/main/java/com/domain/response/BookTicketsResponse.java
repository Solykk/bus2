package com.domain.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by burbulet on 4/25/17.
 */
@NoArgsConstructor
@Data
public class BookTicketsResponse implements Serializable {
    private String bookingId;

    public BookTicketsResponse(String bookingId) {
        this.bookingId = bookingId;
    }
}
