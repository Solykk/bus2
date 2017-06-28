package com.services;

import com.domain.entities.StationEntity;
import com.domain.request.StationRequest;

/**
 * @author Dmitriy Lyashenko
 */

public interface StationService {
    StationEntity createStation(StationRequest stationRequest);
    StationEntity findByAddress(String address);
    StationEntity findById(Long id);
    StationEntity findByCoordinateId(Long id);
    StationEntity findOne(StationRequest stationRequest);
}
