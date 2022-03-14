package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {

    private UsersService usersService;
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    ApiExceptions apiExceptions;

    @GetMapping(path = "/getAllActiveUsers")
    public List<Users> viewActiveUsers(){
        return usersService.getActiveUsers();
    }

    @GetMapping(path = "/getAllInactiveUsers")
    public List<Users> viewInactiveUsers(){
        return usersService.getInactiveUsers();
    }

    @GetMapping(path = "/getAllDeletedUsers")
    public List<Users> viewDeletedUsers(){
        return usersService.getDeletedUsers();
    }

    @GetMapping(path = "/getAllSuspendedUsers")
    public List<Users> viewSuspendedUsers(){
        return usersService.getSuspendedUsers();
    }

    @GetMapping(path = "/listUser/{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") Long userId) {
        return usersService.findUserById(userId);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Users> login(@RequestBody Users users) throws Exception {
        return ResponseEntity.ok().body(usersService.login(users));
    }

    @PostMapping(path = "/registerUsers")
    public ResponseEntity<?> registerUser(@RequestBody Users users){
        usersService.addNewUser(users);

        apiExceptions=new ApiExceptions("The user "+users.getFirstName()+" "+users.getLastName()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/registerUsers");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @PutMapping(path = "/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,
                                        @RequestBody Users user){
        usersService.updateUser(userId,user);

        apiExceptions=new ApiExceptions("The user id "+userId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/users/updateUser");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }

    @PutMapping(path = "/updateEmailAddress")
    public ResponseEntity<?> updateEmailAddress(@RequestBody String oldEmailAddress,
                                                @RequestBody String newEmailAddress){
        usersService.changeEmailAddress(oldEmailAddress,newEmailAddress);

        apiExceptions=new ApiExceptions("Email address "+oldEmailAddress
                +" was updated to "+newEmailAddress,HttpStatus.OK,LocalDateTime.now(),"api/v1/users/updateEmailAddress");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }

    @DeleteMapping(path = "/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        usersService.deleteUser(userId);
    }

    @PostMapping(path = "/suspendUser/{userId}")
    public ResponseEntity<?> suspendUser(@PathVariable("userId") Long userId){
        usersService.suspendUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("The user has been suspended.");
    }

    @PostMapping(path = "/restoreUser/{userId}")
    public ResponseEntity<?> restoreUser(@PathVariable("userId") Long userId){
        usersService.restoreUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("The user has been restored.");
    }
}
