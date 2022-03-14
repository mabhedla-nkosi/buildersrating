package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.groups.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface BusinessTypesRepository extends JpaRepository<BusinessTypes,Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM BusinessTypes c WHERE c.businessType = ?1")
    boolean existsByBType(@RequestBody String businessType);

    @Query(value="SELECT * FROM tbl_business_types g WHERE g.deleted=0 AND g.suspended=0", nativeQuery = true)
    public List<BusinessTypes> findAllActiveBTypes();

    @Query(value="SELECT * FROM tbl_business_types g WHERE g.deleted=1 OR g.suspended=1", nativeQuery = true)
    public List<BusinessTypes> findAllInactiveBTypes();

    @Query(value="SELECT * FROM tbl_business_types g WHERE g.deleted=1", nativeQuery = true)
    public List<BusinessTypes> findAllDeletedBTypes();

    @Query(value="SELECT * FROM tbl_business_types g WHERE g.suspended=1", nativeQuery = true)
    public List<BusinessTypes> findAllSuspendedBTypes();
}
