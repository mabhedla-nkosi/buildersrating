package com.buildersrating.buildersrating.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Business c WHERE c.bRegNumber = ?1")
    boolean existsByRegNumber(@RequestBody String bRegNumber);
}
