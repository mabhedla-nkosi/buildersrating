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

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    ApiExceptions apiExceptions;

    @GetMapping(path = "/get-all-users")
    public List<Users> viewUsers(){
        try{
            return usersService.getUsers();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/registerUsers")
    public ResponseEntity<?> registerUser(@RequestBody Users users){
        if(users.getFirstName().length()<3){
            throw new ApiRequestException("The name is too short. Please enter a proper name.");
        }else if(users.getLastName().length()<3){
            throw new ApiRequestException("The name is too short. Please enter a proper name.");
        }else if(users.getIdNumber().length()<13||users.getIdNumber().length()>13){
            throw new ApiRequestException("The ID number is incorrect. Please enter 13 digits");
        }

        try{
            usersService.addNewUser(users);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        apiExceptions=new ApiExceptions("The user "+users.getFirstName()+" "+users.getLastName()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/registerUsers");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,
                                        @RequestParam(required = false)String firstName,
                                        @RequestParam(required = false)String lastName,
                                        @RequestParam(required = false)String password,
                                        @RequestParam(required = false)String emailAddress,
                                        @RequestParam(required = false)String userStatus,
                                        @RequestParam(required = false)Long groupId){

        try{
            usersService.updateUser(userId,firstName,lastName,password,emailAddress,userStatus,groupId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The user id "+userId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/users");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        try{
            usersService.deleteUser(userId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
