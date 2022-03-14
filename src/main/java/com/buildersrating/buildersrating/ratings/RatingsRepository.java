package com.buildersrating.buildersrating.ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Long> {

    @Query(value = "SELECT * FROM tbl_ratings r WHERE t.deleted=0 AND t.suspended=0",nativeQuery = true)
    List<Ratings> findAllActiveRating();

    @Query(value = "SELECT * FROM tbl_ratings r WHERE t.deleted=0 OR t.suspended=0",nativeQuery = true)
    List<Ratings> findAllInactiveRating();

    @Query(value = "SELECT * FROM tbl_ratings r WHERE t.deleted=0",nativeQuery = true)
    List<Ratings> findAllDeletedRating();

    @Query(value = "SELECT * FROM tbl_ratings r WHERE t.suspended=0",nativeQuery = true)
    List<Ratings> findAllSuspendedRating();
}
