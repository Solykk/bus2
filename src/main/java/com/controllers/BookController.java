package com.controllers;

import com.domain.response.BookTicketsResponse;
import com.domain.request.BookTicketsRequest;
import com.exceptions.BookingFailedException;
import com.services.BookingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by burbulet on 4/24/17.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOG = Logger.getLogger(BookController.class);

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    /*
    REQUEST
    curl -H "Content-Type: application/json" -X POST -d '{"startCityId":"1","endCityId":"35","startDate":"11-05-2017","returnDate":"11-06-2017","passengers":"4","name":"Vasya","surname":"Pupkin","email":"dododo@i.ua","phone":"+380505555555","message":"please book me some tickets"}' http://localhost:8000/book

    RETURN:
    {"bookingId":"c0ea1c"}
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<BookTicketsResponse> book(@RequestBody @Valid BookTicketsRequest request) {
        LOG.info("book tickets " + request);
        try {
            String bookingId = bookingService.book(request);
            LOG.info("booking success " + bookingId);
            BookTicketsResponse response = new BookTicketsResponse(bookingId);
            return ResponseEntity.ok(response);
        } catch (BookingFailedException e) {
            LOG.info("failed to create booking");
            return ResponseEntity.badRequest().build();
        }
    }
}
