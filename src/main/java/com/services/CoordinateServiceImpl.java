package com.services;

import com.domain.entities.CoordinateEntity;
import com.domain.request.CoordinateRequest;
import com.exceptions.DuplicateCoordinateException;
import com.repositories.CoordinateRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dmitriy Lyashenko
 */

@Service
public class CoordinateServiceImpl implements CoordinateService {

    private static final Logger LOG = Logger.getLogger(CoordinateServiceImpl.class);

    private CoordinateRepository coordinateRepository;

    @Override
    public CoordinateEntity createCoordinate(CoordinateRequest coordinateRequest) {
        if(findCoordinate(coordinateRequest.getLongitude(), coordinateRequest.getLatitude()) != null){
            throw new DuplicateCoordinateException("Coordinate with longitude " + coordinateRequest.getLongitude() +
            " and latitude " + coordinateRequest.getLatitude() + " already exist");
        }

        CoordinateEntity coordinate = new CoordinateEntity(coordinateRequest.getLongitude(),
                                                           coordinateRequest.getLatitude());
        return coordinateRepository.save(coordinate);
    }

    @Override
    public CoordinateEntity findCoordinate(Float longitude, Float latitude) {
        return coordinateRepository.findOneByLongitudeAndLatitude(longitude, latitude);
    }

    @Override
    public CoordinateEntity findOneById(Long id) {
        return coordinateRepository.findOne(id);
    }

    @Override
    public CoordinateEntity findOne(CoordinateRequest coordinateRequest) {
        return coordinateRepository.findOneByLongitudeAndLatitude(coordinateRequest.getLongitude(), coordinateRequest.getLatitude());
    }

    @Autowired
    public void setCoordinateRepository(CoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }
}
