/**
 * Created by zeruch on 30/03/17.
 */
package com.repositories;

import com.domain.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    CityEntity findOneByCode(String code);
}
