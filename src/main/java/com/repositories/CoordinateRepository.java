package com.repositories;

import com.domain.entities.CoordinateEntity;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Lyashenko
 */

@Repository
public interface CoordinateRepository extends CrudRepository<CoordinateEntity, Long> {
    CoordinateEntity findOneByLongitudeAndLatitude(Float longitude, Float latitude);
}
