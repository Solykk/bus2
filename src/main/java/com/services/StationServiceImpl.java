package com.services;

import com.domain.entities.CoordinateEntity;
import com.domain.entities.StationEntity;
import com.domain.request.StationRequest;
import com.exceptions.DuplicateStationException;
import com.exceptions.NonCorrespondenceStationCoordinateException;
import com.repositories.StationRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dmitriy Lyashenko
 */

@Service
public class StationServiceImpl implements StationService {

    private static final Logger LOG = Logger.getLogger(StationServiceImpl.class);

    private StationRepository stationRepository;
    private CoordinateService coordinateService;

    @Override
    public StationEntity createStation(StationRequest stationRequest) {
        LOG.info("StationService.createStation in: stationResult: " + stationRequest.toString());

        StationEntity stationDb = findOne(stationRequest);
        if(stationDb != null){
            LOG.warn("  Station with address " + stationRequest.getAddress() + " and coordinate "
                    + stationRequest.getCoordinate().toString() + " already exist");
            throw new DuplicateStationException("Station with address " + stationRequest.getAddress() + " and coordinate "
                    + stationRequest.getCoordinate().toString() + " already exist");
        }

        StationEntity stationByAd = stationRepository.findOneByAddress(stationRequest.getAddress());
        CoordinateEntity coordinateDb = coordinateService.findOne(stationRequest.getCoordinate());
        if(stationByAd != null && coordinateDb != null && !stationByAd.getCoordinate().equals(coordinateDb)){
            LOG.warn("  Coordinates " + stationRequest.getCoordinate().toString()+
                    " do not correspond to the station with address " + stationRequest.getAddress());
            throw new NonCorrespondenceStationCoordinateException("Coordinates " + stationRequest.getCoordinate().toString()+
                    " do not correspond to the station with address " + stationRequest.getAddress());
        }

        CoordinateEntity coordinate = coordinateService.createCoordinate(stationRequest.getCoordinate());
        StationEntity station = new StationEntity(stationRequest.getAddress(), coordinate);
        StationEntity stationResult = stationRepository.save(station);
        LOG.info("StationService.createStation before out: stationResult: " + stationResult.toString());
        return stationResult;
    }

    @Override
    public StationEntity findByAddress(String address) {
        return stationRepository.findOneByAddress(address);
    }

    @Override
    public StationEntity findById(Long id) {
        return stationRepository.findOne(id);
    }

    @Override
    public StationEntity findByCoordinateId(Long id) {
        return stationRepository.findOneByCoordinate_Id(id);
    }

    @Override
    public StationEntity findOne(StationRequest stationRequest) {
        LOG.info("StationService.findOne in: stationRequest: " + stationRequest.toString());
        CoordinateEntity coordinateEntity = coordinateService.findOne(stationRequest.getCoordinate());
        if(coordinateEntity == null){
            LOG.info(   "StationService.findOne: coordinateEntity == null");
            return null;
        }

        StationEntity stationResult = stationRepository.findOneByAddressAndCoordinate_Id(stationRequest.getAddress(),
                coordinateEntity.getId());
        LOG.info("StationService.findOne before out: stationResult: " + stationResult.toString());
        return stationResult;
    }

    @Autowired
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Autowired
    public void setCoordinateService(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }
}
