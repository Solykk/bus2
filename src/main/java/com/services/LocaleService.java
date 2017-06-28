package com.services;

import com.exceptions.DuplicateLocaleException;
import com.exceptions.LocaleNotFoundException;
import com.domain.entities.CityEntity;
import com.domain.entities.CityLocale;
import com.domain.entities.Locale;
import com.domain.request.CreateCityLocaleRequest;

/**
 * Created by burbulet on 4/27/17.
 */
public interface LocaleService {
    CityLocale findCityLocale(String localeCode, String cityCode) throws LocaleNotFoundException;
    Locale createLocale(String localeCode) throws DuplicateLocaleException;
    CityLocale createCityLocale(CityEntity city, String cityCode, CreateCityLocaleRequest request) throws LocaleNotFoundException, DuplicateLocaleException;
}
