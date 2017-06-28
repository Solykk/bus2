package com.services;

import com.domain.entities.StopEntity;
import com.domain.request.StopRequest;

import java.util.List;

/**
 * @author Dmitriy Lyashenko
 */

public interface StopService {
    StopEntity createStop(StopRequest stopRequest);
    StopEntity findOne(StopRequest stopRequest);
    StopEntity findOneById(Long stopId);
    List<StopEntity> createStops(List<StopEntity> stops);
}
