package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.groups.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Users c WHERE c.idNumber = ?1")
    boolean existsByIdNumber(@RequestBody String idNumber);

    @Query("Select CASE WHEN COUNT(c) > 0 THEN true ELSE false END from Users c where c.emailAddress=?1")
    boolean existsByEmailAddress(@RequestBody String emailAddress);

    @Query(value="SELECT * FROM tbl_users g WHERE g.deleted=0 AND g.suspended=0", nativeQuery = true)
    public List<Users> findAllActiveUsers();

    @Query(value="SELECT * FROM tbl_users g WHERE g.deleted=1 OR g.suspended=1", nativeQuery = true)
    public List<Users> findAllInactiveUsers();

    @Query(value="SELECT * FROM tbl_users g WHERE g.deleted=1", nativeQuery = true)
    public List<Users> findAllDeletedUsers();

    @Query(value="SELECT * FROM tbl_users g WHERE g.suspended=1", nativeQuery = true)
    public List<Users> findAllSuspendedUsers();

    @Query("SELECT e FROM Users e WHERE e.emailAddress=?1")
    Users findByEmail(String emailAddress);
}
