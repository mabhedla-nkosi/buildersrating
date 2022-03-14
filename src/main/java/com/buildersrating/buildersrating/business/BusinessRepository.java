package com.buildersrating.buildersrating.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Business c WHERE c.bRegNumber = ?1")
    boolean existsByRegNumber(@RequestBody String bRegNumber);

    @Query(value = "SELECT * FROM tbl_business b WHERE b.deleted=0 AND b.suspended=0", nativeQuery = true)
    List<Business> findAllActive();

    @Query(value = "SELECT * FROM tbl_business b WHERE b.deleted=1 OR b.suspended=1", nativeQuery = true)
    List<Business> findAllInactive();

    @Query(value = "SELECT * FROM tbl_business b WHERE b.deleted=1", nativeQuery = true)
    List<Business> findAllDeleted();

    @Query(value = "SELECT * FROM tbl_business b WHERE b.suspended=1", nativeQuery = true)
    List<Business> findAllSuspended();
}
