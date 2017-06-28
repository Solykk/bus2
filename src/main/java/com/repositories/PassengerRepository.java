/**
 * Created by zeruch on 30/03/17.
 */
package com.repositories;

import com.domain.entities.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PassengerEntity p WHERE p.phone = :phone")
    boolean existsByPhone(@Param("phone") String phone);

    PassengerEntity findOneByPhone(String phone);
}