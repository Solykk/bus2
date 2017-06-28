/**
 * Created by zeruch on 06/04/17.
 */
package com.services;

import com.domain.dtos.CityDto;
import com.exceptions.*;
import com.domain.entities.CityEntity;
import com.domain.request.CreateCityRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CityService {
    List<CityDto> findAllReachableCities(String locale, Long startCityId) throws CityNotFoundException;

    List<CityDto> findAllCities(String locale);

    CityDto findById(Long id) throws CityNotFoundException;

    CityDto findById(String locale, Long id) throws CityNotFoundException;

    CityDto createCity(CreateCityRequest request) throws DuplicateCityException, LocaleNotFoundException;

    BigDecimal findRateOneWay(Long startCity, Long endCity) throws CityNotFoundException, DestinationNotFoundException;

    BigDecimal findRateReturn(Long startCity, Long endCity) throws CityNotFoundException, DestinationNotFoundException;

    void createDestination(String startCity, String endCity, BigDecimal oneWayPrice, BigDecimal returnPrice) throws CityNotFoundException, DuplicateDestinationException;

    CityEntity findByCode(String code);
}
