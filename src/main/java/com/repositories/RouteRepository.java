/**
 * Created by zeruch on 30/03/17.
 */
package com.repositories;

import com.domain.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findOneByNumber(Integer number);
}
