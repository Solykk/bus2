/**
 * Created by zeruch on 30/03/17.
 */
package com.repositories;

import com.domain.entities.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {
    TripEntity findOneByRouteId(Long routeId);
}
