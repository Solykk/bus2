package com.controllers;

import com.exceptions.CityNotFoundException;
import com.domain.response.PriceResponse;
import com.exceptions.DestinationNotFoundException;
import com.repositories.DestinationRepository;
import com.services.CityService;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * Created by burbulet on 4/30/17.
 */

@RestController
@Validated
@RequestMapping("/{locale}/prices")
public class PriceController {

    private static final Logger LOG = Logger.getLogger(PriceController.class);

    private DestinationRepository destinationRepository;

    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /*
        sample request:
        curl -X GET "localhost:8000/ru/prices/filter?startCity=1&endCity=35&startDate=11-05-2017&returnDate=11-06-2017&passengers=4"
        */
    @CrossOrigin
    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public ResponseEntity<PriceResponse> findPrice(@Length(min = 2, max = 2) @PathVariable("locale") String locale,
                                                   @RequestParam("startCity") Long startCityId,
                                                   @RequestParam("endCity") Long endCityId,
                                                   @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-(201[7-9]|202[0-9])$") @RequestParam("startDate") String startDate,
                                                   @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-(201[7-9]|202[0-9])$") @RequestParam(value = "returnDate", required = false) String returnDate,
                                                   @Min(1) @Max(4) @RequestParam("passengers") Integer passengers)
    {
        try {
            BigDecimal rate;
            if (returnDate != null) rate = cityService.findRateReturn(startCityId, endCityId);
            else rate = cityService.findRateOneWay(startCityId, endCityId);
            LOG.info("did fing price = " + rate);
            BigDecimal price = rate != null ? rate.multiply(new BigDecimal(passengers)) : null;
            return ResponseEntity.ok(new PriceResponse(rate, price));
        } catch (CityNotFoundException e) {
            LOG.info("ERROR find city start city " + startCityId + " end city " + endCityId);
            return ResponseEntity.badRequest().build();
        } catch (DestinationNotFoundException e) {
            LOG.info("ERROR find destination start city " + startCityId + " end city " + endCityId);
            return ResponseEntity.badRequest().build();
        }
    }
}
