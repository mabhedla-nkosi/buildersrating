package com.buildersrating.buildersrating.businesstypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface BusinessTypesRepository extends JpaRepository<BusinessTypes,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM BusinessTypes c WHERE c.businessType = ?1")
    boolean existsByBType(@RequestBody String businessType);
}
