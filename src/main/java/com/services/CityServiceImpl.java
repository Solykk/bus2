/**
 * Created by zeruch on 06/04/17.
 */
package com.services;

import com.exceptions.*;
import com.domain.dtos.CityDto;
import com.domain.entities.CityEntity;
import com.domain.entities.CityLocale;
import com.domain.entities.Destination;
import com.domain.request.CreateCityLocaleRequest;
import com.domain.request.CreateCityRequest;
import com.repositories.CityRepository;
import com.repositories.DestinationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOG = Logger.getLogger(CityServiceImpl.class);

    private CityRepository cityRepository;
    private LocaleService localeService;
    private DestinationRepository destinationRepository;

    @Autowired
    public void setLocaleService(LocaleService localeService) {
        this.localeService = localeService;
    }

    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<CityDto> findAllCities(String localeCode) {
        Iterable<CityEntity> cityEntities = cityRepository.findAll();
        List<CityDto> cityDtoList = new ArrayList<>();

        for (CityEntity cityEntity : cityEntities) {
            String code = cityEntity.getCode();
            String image = cityEntity.getImageUrl();
            String name = "" + code;
            try {
                CityLocale locale = localeService.findCityLocale(localeCode, code);
                name = locale.getName();
            } catch (LocaleNotFoundException e) {
                LOG.info("failed to find locale " + localeCode + " for city " + code);
            }
            CityDto.CityDtoBuilder builder = new CityDto
                    .CityDtoBuilder(cityEntity.getId())
                    .name(name)
                    .image(image);
            CityDto cityDto = builder.build();
            cityDtoList.add(cityDto);
        }

        LOG.info("findAllCities(): " + cityDtoList);
        return cityDtoList;
    }

    @Override
    public List<CityDto> findAllReachableCities(String localeCode, Long startCityId) {
        CityEntity startCity = cityRepository.findOne(startCityId);
        if (startCity == null) throw new CityNotFoundException("city not found");

        List<Destination> destinations = startCity.getDestinations();
        List<CityDto> cityDtoList = new ArrayList<>();

        for (Destination destination : destinations) {
            CityEntity endCity = destination.getEndCity();
            String image = endCity.getImageUrl();
            String code = endCity.getCode();
            String name = "" + code;
            try {
                CityLocale locale = localeService.findCityLocale(localeCode, code);
                name = locale.getName();
            } catch (LocaleNotFoundException e) {
                LOG.info("failed to find locale " + localeCode + " for city " + code);
            }
            CityDto.CityDtoBuilder builder = new CityDto.CityDtoBuilder(endCity.getId()).name(name).image(image);
            CityDto cityDto = builder.build();
            cityDtoList.add(cityDto);
        }

        LOG.info("findAllReachableCities(" + startCity +"): " + cityDtoList);
        return cityDtoList;
    }

    @Override
    public CityDto findById(Long id) {
        CityEntity cityEntity = cityRepository.findOne(id);
        if (cityEntity == null) throw new CityNotFoundException("city not found");
        CityDto cityDto = new CityDto
                .CityDtoBuilder(cityEntity.getId())
                .code(cityEntity.getCode())
                .build();
        return cityDto;
    }

    @Override
    public CityDto findById(String localeCode, Long id) throws CityNotFoundException {
        CityEntity cityEntity = cityRepository.findOne(id);
        if (cityEntity == null) throw new CityNotFoundException("city not found");
        String name = cityEntity.getCode();
        try {
            CityLocale locale = localeService.findCityLocale(localeCode, cityEntity.getCode());
            name = locale.getName();
        } catch (LocaleNotFoundException e) {
            LOG.info("failed to find locale " + localeCode + " for city " + name);
        }
        CityDto cityDto = new CityDto
                .CityDtoBuilder(cityEntity.getId())
                .name(name)
                .build();
        return cityDto;
    }

    @Override
    public CityDto createCity(CreateCityRequest request) {
        String code = request.getCode().toUpperCase();
        CityEntity cityEntity = cityRepository.findOneByCode(code);
        if (cityEntity != null) throw new DuplicateCityException("city with code " + code + " already exists");

        Set<CityLocale> locales = new HashSet<>();
        for (CreateCityLocaleRequest localeRequest : request.getLocales()) {
            try {
                CityLocale cityLocale = localeService.createCityLocale(cityEntity, code, localeRequest);
                locales.add(cityLocale);
            } catch (DuplicateLocaleException e) {
                throw new DuplicateCityException("locale " +request.getCode() + " for city with code " + code + " already exists");
            }
        }

        cityEntity = cityRepository.save(new CityEntity(code, request.getImageUrl()));

        CityDto cityDto = new CityDto.CityDtoBuilder(cityEntity.getId()).code(cityEntity.getCode()).build();
        return cityDto;
    }

    @Override
    public BigDecimal findRateOneWay(Long startCity, Long endCity) throws CityNotFoundException {
        Destination destination = findDestination(startCity, endCity);
        LOG.info("did find one way price " + destination.getPriceOneWay() + "return price" + destination.getPriceReturn());
        return destination.getPriceOneWay();    }

    @Override
    public BigDecimal findRateReturn(Long startCity, Long endCity) throws CityNotFoundException {
        Destination destination = findDestination(startCity, endCity);
        LOG.info("did find one way price " + destination.getPriceOneWay() + "return price" + destination.getPriceReturn());
        return destination.getPriceReturn();
    }

    @Override
    public void createDestination(String startCity, String endCity, BigDecimal oneWayPrice, BigDecimal returnPrice) {
        CityEntity startCityEntity = cityRepository.findOneByCode(startCity);
        CityEntity endCityEntity = cityRepository.findOneByCode(endCity);
        if (startCityEntity == null || endCityEntity == null) throw new CityNotFoundException("could not find city");

        try {
            Destination destination = findDestination(startCityEntity.getId(), endCityEntity.getId());
            LOG.info("destination already exists" + destination);
            throw new DuplicateDestinationException("destination already exists");
        } catch (DestinationNotFoundException e) {
            destinationRepository.save(new Destination(startCityEntity, endCityEntity, oneWayPrice, returnPrice));
            destinationRepository.save(new Destination(endCityEntity, startCityEntity, oneWayPrice, returnPrice));
        }
    }

    @Override
    public CityEntity findByCode(String code) {
        return cityRepository.findOneByCode(code);
    }

    private Destination findDestination(Long startCity, Long endCity) throws CityNotFoundException, DestinationNotFoundException{
        CityEntity startCityEntity = cityRepository.findOne(startCity);
        CityEntity endCityEntity = cityRepository.findOne(endCity);
        if (startCityEntity == null || endCityEntity == null) throw new CityNotFoundException("could not find city");

        Destination destination = destinationRepository.findOneByStartCityAndEndCity(startCityEntity, endCityEntity);
        if (destination == null) throw new DestinationNotFoundException("failed to find destination from city " + startCity + " to city " + endCity);
        LOG.info("did find destination " + destination);
        return destination;
    }
}
