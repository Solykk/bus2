package com.repositories;

import com.domain.entities.Locale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by burbulet on 4/26/17.
 */

@Repository
public interface LocaleRepository extends CrudRepository <Locale, Long> {
    Locale findByCode(String code);
}
