package com.buildersrating.buildersrating.groups;

import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupsController {

    @Autowired
    GroupsService groupsService;

    @PostMapping
    public ResponseEntity<?> registerNewGroup(@RequestBody Groups groups){
        ApiExceptions apiExceptions=null;
        if(groups.getGroupName().isEmpty()){
            //throw new ApiRequestException("There is no value.");
            apiExceptions=new ApiExceptions("There is no value.","400",LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptions);
        }else if(groups.getGroupName().length()<3){ //not a correct validation
         //   throw new ApiRequestException("You can't enter a number.");
            apiExceptions=new ApiExceptions("You can't enter a number.","400",LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptions);
        }
        try{
            groupsService.addNewGroup(groups);
        }catch (IllegalArgumentException e){
           // throw new ApiRequestException(e.getMessage());
            apiExceptions=new ApiExceptions(e.getMessage(),"400",LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptions);
        }
        catch (Exception e){
            apiExceptions=new ApiExceptions(e.getMessage(),"400",LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptions);
        }
        apiExceptions=new ApiExceptions("The Group has been created.","201",LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @GetMapping
    public List<Groups> viewGroups(){
        return groupsService.getGroups();
    }
}
