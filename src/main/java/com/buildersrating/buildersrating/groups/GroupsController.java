package com.buildersrating.buildersrating.groups;

import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupsController {

    private GroupsService groupsService;
    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    ApiExceptions apiExceptions;

    @PostMapping(path = "/registerGroup")
    public ResponseEntity<?> createNewGroup(@RequestBody Groups groups){
        groupsService.addNewGroup(groups);
        apiExceptions=new ApiExceptions("The group "+groups.getGroupName()
                +" was created.",HttpStatus.CREATED,LocalDateTime.now(),"api/v1/groups/registerGroups");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @GetMapping(path = "/listAllActiveGroups")
    public List<Groups> viewActiveGroups(){
        return groupsService.getActiveGroups();
    }

    @GetMapping(path = "/listAllInactiveGroups")
    public List<Groups> viewInactiveGroups(){
        return groupsService.getInactiveGroups();
    }

    @GetMapping(path = "/listDeletedGroups")
    public List<Groups> viewDeletedGroups(){
        return groupsService.getDeletedGroups();
    }

    @GetMapping(path = "/listGroups/{groupId}")
    public Optional<Groups> getGroupById(@PathVariable("groupId") Long groupId) {
        return groupsService.findGroupById(groupId);
    }

    @GetMapping(path = "/listSuspendedGroups")
    public List<Groups> viewSuspendedGroups(){
        return groupsService.getSuspendedGroups();
    }

    @DeleteMapping(path="/deleteGroup/{groupId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId){
        groupsService.deleteGroups(groupId);
    }

    @PostMapping(path = "suspendGroup/{groupId}")
    public void suspendGroup(@PathVariable("groupId") Long groupId){
        groupsService.suspendGroup(groupId);
    }

    @PostMapping(path = "restore/{groupId}")
    public void restoreGroup(@PathVariable("groupId") Long groupId){
        groupsService.restoreGroup(groupId);
    }

    @PutMapping(path = "/updateGroup/{groupId}")
    public ResponseEntity<?> updateGroups(@PathVariable("groupId") Long groupId,
                                          @RequestBody Groups groups){
        groupsService.updateGroups(groupId,groups);
        apiExceptions=new ApiExceptions("The group id "+groupId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/groups");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }


}
