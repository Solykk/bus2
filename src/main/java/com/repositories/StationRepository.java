package com.repositories;

import com.domain.entities.StationEntity;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy Lyashenko
 */

@Repository
public interface StationRepository extends CrudRepository<StationEntity, Long> {
    StationEntity findOneByAddress(String address);
    StationEntity findOneByCoordinate_Id(Long id);
    StationEntity findOneByAddressAndCoordinate_Id(String address, Long coordinateId);
}
