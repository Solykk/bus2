package com.repositories;

import com.domain.entities.CityLocale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by burbulet on 4/26/17.
 */

@Repository
public interface CityLocalesRepository extends CrudRepository<CityLocale, Long> {
    CityLocale findByCodeAndLocaleCode(String code, String localeCode);
}
