package com.buildersrating.buildersrating.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/groups")
public class GroupsController {

    @Autowired
    GroupsService groupsService;

    @PostMapping
    public void registerNewGroup(@RequestBody Groups groups){
        groupsService.addNewGroup(groups);
    }
}
