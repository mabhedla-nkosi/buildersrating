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

    ApiExceptions apiExceptions;

    @PostMapping(path = "/registerGroups")
    public ResponseEntity<?> registerNewGroup(@RequestBody Groups groups){
        if(groups.getGroupName().isEmpty()){
            throw new ApiRequestException("There is no value.");
        }else if(groups.getGroupName().length()<3){ //not a correct validation
            throw new ApiRequestException("You can't enter a number.");
        }
        try{
            groupsService.addNewGroup(groups);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The group "+groups.getGroupName()
                +" was created.",HttpStatus.CREATED,LocalDateTime.now(),"api/v1/groups/registerGroups");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @GetMapping(path = "/list-all-groups")
    public List<Groups> viewGroups(){
        try {
            return groupsService.getGroups();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @DeleteMapping(path="{groupId}")
    public void deleteGroups(@PathVariable("groupId") Long groupId){
        try{
            groupsService.deleteGroups(groupId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping(path = "{groupId}")
    public ResponseEntity<?> updateGroups(@PathVariable("groupId") Long groupId,
                                          @RequestParam(required = false)String groupName){

        try{
            groupsService.updateGroups(groupId,groupName);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The group id "+groupId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/groups");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }


}
