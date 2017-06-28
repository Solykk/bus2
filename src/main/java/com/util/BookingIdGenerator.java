package com.util;

import java.util.UUID;

/**
 * Created by burbulet on 4/24/17.
 */
public final class BookingIdGenerator {

    public String generate() {
        String uuid = UUID.randomUUID().toString();
        String id = uuid.substring(0,6);
        return id;
    }

}
