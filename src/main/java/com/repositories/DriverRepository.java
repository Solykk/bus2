/**
 * Created by zeruch on 01/04/17.
 */
package com.repositories;

import com.domain.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
}
