package com.buildersrating.buildersrating.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {

    //boolean exists(String groupName);
   // @Query("Select s from Groups s where s.groupName = ?1")
 //   Optional<Groups> findGroupName(String groupName);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Groups c WHERE c.groupName = ?1")
    boolean existsByName(@RequestBody String groupName);

    @Query(value="SELECT * FROM tbl_groups g WHERE g.deleted=0 AND g.suspended=0", nativeQuery = true)
    public List<Groups> findAllActiveGroups();

    @Query(value="SELECT * FROM tbl_groups g WHERE g.deleted=1 OR g.suspended=1", nativeQuery = true)
    public List<Groups> findAllInactiveGroups();

    @Query(value="SELECT * FROM tbl_groups g WHERE g.deleted=1", nativeQuery = true)
    public List<Groups> findAllDeletedGroups();

    @Query(value="SELECT * FROM tbl_groups g WHERE g.suspended=1", nativeQuery = true)
    public List<Groups> findAllSuspendedGroups();
}
