package com.services;

import com.exceptions.DuplicateRouteException;
import com.domain.entities.RouteEntity;
import com.domain.entities.StopEntity;
import com.domain.request.RouteRequest;
import com.domain.request.StopRequest;
import com.repositories.RouteRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Lyashenko
 */

@Service
public class RouteServiceImpl implements RouteService{

    private static final Logger LOG = Logger.getLogger(RouteServiceImpl.class);

    private RouteRepository routeRepository;
    private StopService stopService;

    @Transactional
    @Override
    public List<RouteEntity> createRoutes(ArrayList<RouteRequest> routes) {
        List<RouteEntity> result = new ArrayList<>();

        for (RouteRequest route : routes) {
            if(routeRepository.findOneByNumber(route.getNumber()) != null){
                throw new DuplicateRouteException("Route with number " + route.getNumber() + " already exist");
            }
        }

        for (RouteRequest route : routes) {
            if(routeRepository.findOneByNumber(route.getNumber()) == null){
                List<StopEntity> stops = new ArrayList<>();
                for (StopRequest stop : route.getStops()) {
                    if(stopService.findOne(stop) != null){
                        stops.add(stopService.findOne(stop));
                    } else {
                        StopEntity stopDb = stopService.createStop(stop);
                        stops.add(stopDb);
                    }
                }
                RouteEntity routeEntity = routeRepository.save(new RouteEntity(route.getNumber(), stops));
                result.add(routeEntity);
            }
        }
        return result;
    }

    @Override
    public List<RouteEntity> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Autowired
    public void setStopService(StopService stopService) {
        this.stopService = stopService;
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
