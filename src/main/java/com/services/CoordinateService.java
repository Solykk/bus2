package com.services;

import com.domain.request.CoordinateRequest;
import com.domain.entities.CoordinateEntity;

/**
 * @author Dmitriy Lyashenko
 */

public interface CoordinateService {
    CoordinateEntity createCoordinate(CoordinateRequest coordinateRequest);
    CoordinateEntity findCoordinate(Float longitude, Float latitude);
    CoordinateEntity findOneById(Long id);
    CoordinateEntity findOne(CoordinateRequest coordinateRequest);
}
