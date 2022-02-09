package com.buildersrating.buildersrating.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {

    //boolean exists(String groupName);
   // @Query("Select s from Groups s where s.groupName = ?1")
 //   Optional<Groups> findGroupName(String groupName);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Groups c WHERE c.groupName = ?1")
    boolean existsByName(@RequestBody String groupName);


    //Groups findAllById(Long groupId);
}
