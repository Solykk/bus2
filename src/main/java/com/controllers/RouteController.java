package com.controllers;

import com.domain.request.RouteRequest;
import com.services.RouteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Dmitriy Lyashenko
 */
@RestController
@Validated
@RequestMapping("/routes")
public class RouteController {

    private static final Logger LOG = Logger.getLogger(RouteController.class);

    private RouteService routeService;

    //curl -H "Content-Type: application/json" -X POST -d '[{"number":123,"stops":[{"city":"kiev","station":{"address":"Zavodskaya Str, 11","coordinate":{"latitude":123.22,"longitude":122.589}}},{"city":"lvov","station":{"address":"Franka Str, 12","coordinate":{"latitude":23.88,"longitude":12.99}}}]},{"number":567,"stops":[{"city":"berlin","station":{"address":"Munhen Str, 14","coordinate":{"latitude":3.88,"longitude":2.3}}},{"city":"munich","station":{"address":"Ziben Str, 9","coordinate":{"latitude":67.88,"longitude":8.99}}}]}]' http://localhost:8000/routes
    @PostMapping
    public ResponseEntity<?> addRoutes(@RequestBody ArrayList<RouteRequest> routes){
        LOG.info("---RouteController.addRoutes in");
        ResponseEntity<?> responseEntity = ResponseEntity.ok(routeService.createRoutes(routes).toString());
        LOG.info("---RouteController.addRoutes before out: response: " + responseEntity.toString());
        return responseEntity;
    }

    //curl -X GET localhost:8000/routes
    @GetMapping
    public ResponseEntity<?> getRoutes(){
        return ResponseEntity.ok(routeService.getAllRoutes().toString());
    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}
