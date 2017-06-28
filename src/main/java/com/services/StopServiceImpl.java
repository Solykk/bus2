package com.services;

import com.domain.request.StopRequest;
import com.exceptions.CityNotFoundException;
import com.domain.entities.CityEntity;
import com.domain.entities.StationEntity;
import com.domain.entities.StopEntity;
import com.exceptions.DuplicateCityStationException;
import com.exceptions.DuplicateStopException;
import com.exceptions.NonCorrespondenceStationCoordinateException;
import com.repositories.StopRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Lyashenko
 */

@Service
public class StopServiceImpl implements StopService {

    private static final Logger LOG = Logger.getLogger(StopServiceImpl.class);

    private StopRepository stopRepository;
    private StationService stationService;
    private CityService cityService;

    @Override
    public StopEntity createStop(StopRequest stopRequest) {
        LOG.info("StopService.createStop in: stopRequest: " + stopRequest.toString());
        if(findOne(stopRequest) != null){
            LOG.warn("  Stop " + stopRequest.toString() + " already exist");
            throw new DuplicateStopException("Stop " + stopRequest.toString() + " already exist");
        }

        CityEntity cityDb = cityService.findByCode(stopRequest.getCity().toUpperCase());
        if(cityDb == null){
            LOG.warn("  City with name " + stopRequest.getCity().toUpperCase() + " not found");
            throw new CityNotFoundException("City with name " + stopRequest.getCity().toUpperCase() + " not found");
        }

        LOG.info("  StopService.createStop cityDb: " + cityDb.toString());
        if(cityDb.getStations() != null && cityDb.getStations().size() != 0){
            for (StationEntity station : cityDb.getStations()) {
                if(station.getAddress().equals(stopRequest.getStation().getAddress())){
                    LOG.warn("City " + cityDb.getCode()+ " already have station with " +
                            "address " + stopRequest.getStation().getAddress());
                    throw new DuplicateCityStationException("City " + cityDb.getCode()+ " already have station with " +
                            "address " + stopRequest.getStation().getAddress());
                }
            }
        }

        StationEntity stationDb = stationService.findByAddress(stopRequest.getStation().getAddress());
        LOG.info("  StopService.createStop stationDb: " + stationDb);
        if(stationDb != null && stationService.findByCoordinateId(stationDb.getCoordinate().getId()).equals(stationDb)){
            LOG.warn("  Coordinates " +
                    stationService.findByCoordinateId(stationDb.getCoordinate().getId()).toString()+
                    " do not correspond to the station with address " + stopRequest.getStation().getAddress());
            throw new NonCorrespondenceStationCoordinateException("Coordinates " +
                    stationService.findByCoordinateId(stationDb.getCoordinate().getId()).toString()+
                    " do not correspond to the station with address " + stopRequest.getStation().getAddress());
        }

        StationEntity station = stationService.createStation(stopRequest.getStation());
        cityDb.getStations().add(station);
        StopEntity stopEntity = new StopEntity(cityDb, station);
        StopEntity stopResult = stopRepository.save(stopEntity);
        LOG.info("StopService.createStop before out: stopResult " + stopResult.toString());
        return stopResult;
    }

    @Override
    public StopEntity findOne(StopRequest stopRequest) {
        LOG.info("StopService.findOne in: stopRequest: " + stopRequest.toString());
        StationEntity stationEntity = stationService.findOne(stopRequest.getStation());
        if(stationEntity == null){
            LOG.info("  StopService.findOne stationEntity == null");
            return null;
        }
        LOG.info("  StopService.findOne stationId: " + stationEntity.toString());
        CityEntity cityEntity = cityService.findByCode(stopRequest.getCity().toUpperCase());
        if(cityEntity == null){
            LOG.info("  StopService.findOne cityEntity == null");
            return null;
        }
        StopEntity stopResult = stopRepository.findOneByStation_IdAndCityId(stationEntity.getId(), cityEntity.getId());
        LOG.info("StopService.findOne before out: cityCode " + cityEntity.getCode());
        LOG.info("StopService.findOne before out: stopResult " + stopResult.toString());
        return stopResult;
    }

    @Override
    public StopEntity findOneById(Long stopId) {
        return stopRepository.findOne(stopId);
    }

    @Override
    public List<StopEntity> createStops(List<StopEntity> stops) {
        return (ArrayList<StopEntity>)stopRepository.save(stops);
    }

    @Autowired
    public void setStopRepository(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }
}
