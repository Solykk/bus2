package com.repositories;

import com.domain.entities.StopEntity;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Lyashenko
 */

@Repository
public interface StopRepository extends CrudRepository<StopEntity, Long> {
    StopEntity findOneByStation_Id(Long id);
    StopEntity findOneByStation_IdAndCityId(Long stationId, Long cityId);
}
