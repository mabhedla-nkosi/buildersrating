package com.buildersrating.buildersrating.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Users c WHERE c.idNumber = ?1")
    boolean existsByIdNumber(@RequestBody String idNumber);
}
