package com.services;

import com.exceptions.DuplicateLocaleException;
import com.exceptions.LocaleNotFoundException;
import com.domain.entities.CityEntity;
import com.domain.entities.CityLocale;
import com.domain.entities.Locale;
import com.domain.request.CreateCityLocaleRequest;
import com.repositories.CityLocalesRepository;
import com.repositories.LocaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by burbulet on 4/27/17.
 */
@Service
public class LocaleServiceImpl implements LocaleService {
    private LocaleRepository localeRepository;
    private CityLocalesRepository cityLocalesRepository;

    @Autowired
    public void setLocaleRepository(LocaleRepository localeRepository) {
        this.localeRepository = localeRepository;
    }

    @Autowired
    public void setCityLocalesRepository(CityLocalesRepository cityLocalesRepository) {
        this.cityLocalesRepository = cityLocalesRepository;
    }

    @Override
    public CityLocale findCityLocale(String localeCode, String cityCode) {
        CityLocale locale = cityLocalesRepository.findByCodeAndLocaleCode(cityCode, localeCode);
        if (locale == null) throw new LocaleNotFoundException("City locale with localeCode " + localeCode + " and CityCode " + cityCode + " not found");
        return locale;
    }

    @Override
    public Locale createLocale(String localeCode) {
        Locale existingLocale = localeRepository.findByCode(localeCode);
        if (existingLocale != null) {
            throw new DuplicateLocaleException("Locale with code " + localeCode + " already exists");
        }
        Locale locale = new Locale(localeCode);
        return localeRepository.save(locale);
    }

    @Override
    public CityLocale createCityLocale(CityEntity city, String cityCode, CreateCityLocaleRequest request) throws LocaleNotFoundException {
        Locale locale = localeRepository.findByCode(request.getLocaleCode());
        if (locale == null) throw new LocaleNotFoundException("Locale with code " + request.getLocaleCode() + " not found");
        CityLocale cityLocale = cityLocalesRepository.findByCodeAndLocaleCode(cityCode, request.getLocaleCode());
        if (cityLocale != null) throw new DuplicateLocaleException("City locale with localeCode " + request.getLocaleCode() + " and CityCode " + cityCode + " already exists");

        cityLocale = new CityLocale(city, cityCode, locale, request.getName());
        return cityLocalesRepository.save(cityLocale);
    }
}
