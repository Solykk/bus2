package com.services;

import com.domain.entities.RouteEntity;
import com.domain.request.RouteRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Lyashenko
 */

public interface RouteService {
    List<RouteEntity> createRoutes(ArrayList<RouteRequest> routes);
    List<RouteEntity> getAllRoutes();
}
