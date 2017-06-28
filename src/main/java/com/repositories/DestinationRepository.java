package com.repositories;

import com.domain.entities.CityEntity;
import com.domain.entities.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by burbulet on 4/30/17.
 */
@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
    Destination findOneByStartCityAndEndCity(CityEntity startCity, CityEntity endCity);
}
