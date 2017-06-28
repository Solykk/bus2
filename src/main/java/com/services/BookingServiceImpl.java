package com.services;

import com.exceptions.BookingFailedException;
import com.exceptions.CityNotFoundException;
import com.domain.dtos.CityDto;
import com.domain.request.BookTicketsInternalRequest;
import com.domain.request.BookTicketsRequest;
import com.exceptions.DestinationNotFoundException;
import com.exceptions.MailSendingFailedException;
import com.util.BookingIdGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by burbulet on 4/24/17.
 */
@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger LOG = Logger.getLogger(BookingService.class);

    private CityService cityService;
    private MailService mailService;
    private BookingIdGenerator generator = new BookingIdGenerator();

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public String book(BookTicketsRequest request) throws BookingFailedException {
        try {
            CityDto fromCity = cityService.findById("ru",request.getStartCityId());
            CityDto toCity = cityService.findById("ru",request.getEndCityId());

            BigDecimal rate;
            if (request.getReturnDate() != null) rate = cityService.findRateReturn(request.getStartCityId(), request.getEndCityId());
            else rate = cityService.findRateOneWay(request.getStartCityId(), request.getEndCityId());

            BigDecimal price = rate != null? rate.multiply(new BigDecimal(request.getPassengers())) : null;

            Date today = new Date();
            Date returnDate = null;
            Date startDate = null;

            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            try {
                String todayFormat = sdf.format(today);
                startDate = sdf.parse(request.getStartDate());
                if (startDate.before(today) && (!todayFormat.equals(request.getStartDate()))) {
                    LOG.info("start date before today");
                    throw new BookingFailedException("start date before today");
                }
                if (request.getReturnDate() != null) {
                    returnDate = sdf.parse(request.getReturnDate());
                    if ((returnDate.before(today) && (!todayFormat.equals(request.getReturnDate()))) || (returnDate.before(startDate) && !(request.getStartDate().equals(request.getReturnDate())))) {
                        LOG.info("return date before today or start date");
                        throw new BookingFailedException("return date before today or start date");
                    }
                }
            } catch (ParseException e) {
                LOG.info("failed to parse from date");
                throw new BookingFailedException("failed to parse from date");
            }
            String createDate = df.format(today);
            String id = generator.generate();
            BookTicketsInternalRequest internalRequest = new BookTicketsInternalRequest(id, request.getEmail(), request.getPhone(), request.getName(), request.getSurname(),  fromCity.getName(), toCity.getName(), request.getStartDate(), request.getReturnDate(), request.getMessage(), request.getPassengers(), createDate, rate, price);
            mailService.send(internalRequest);
            return id;
        } catch (CityNotFoundException e) {
            LOG.info("failed to find start city " + e.getMessage());
            throw new BookingFailedException("invalid start city");
        } catch (MailSendingFailedException e) {
            LOG.info("failed to send booking email " + e.getMessage());
            throw new BookingFailedException("failed to send booing email");
        } catch (DestinationNotFoundException e) {
            LOG.info("failed to find destination " + e.getMessage());
            throw new BookingFailedException("no route");
        }
    }
}
