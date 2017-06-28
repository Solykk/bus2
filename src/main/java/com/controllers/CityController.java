/**
 * Created by zeruch on 06/04/17.
 */
package com.controllers;

import com.domain.dtos.CityDto;
import com.exceptions.CityNotFoundException;
import com.services.CityService;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/{locale}/cities")
public class CityController {

    private static final Logger LOG = Logger.getLogger(CityController.class);

    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /*
    sample request:
    curl -X GET localhost:8000/ru/cities
    */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<CityDto>> findAllCities(@Length(min = 2, max = 2) @PathVariable("locale") String locale) {
        LOG.info("findAllCities()");
        List<CityDto> cities = cityService.findAllCities(locale);
        return ResponseEntity.ok(cities);
    }

    /*
    sample request:
    curl -X GET "localhost:8000/ru/cities/filter?startCity=6524"
    */
    @CrossOrigin
    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public ResponseEntity<List<CityDto>> findAllReachableCities(@Length(min = 2, max = 2) @PathVariable("locale") String locale, @RequestParam("startCity") Long cityId) {
        LOG.info("findAllReachableCities(" + cityId + ")");
        try {
            List<CityDto> reachableCities = cityService.findAllReachableCities(locale, cityId);
            return ResponseEntity.ok(reachableCities);
        } catch (CityNotFoundException e) {
            LOG.info("findAllReachableCities(" + cityId + ")");
            return ResponseEntity.badRequest().build();
        }
    }
}
