package com.repositories;

import com.domain.entities.FirstStartIndicator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by burbulet on 4/29/17.
 */
@Repository
public interface FirstStartIndicatorRepository extends CrudRepository<FirstStartIndicator, Integer>{
}
