package com.buildersrating.buildersrating.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {

    @Autowired
    GroupsRepository groupsRepository;

    public List<Groups> getGroups(){
        return groupsRepository.findAll();
    }

    public void addNewGroup(Groups groups){
            if(!groupsRepository.existsByName(groups.getGroupName())){
             try{

        groupsRepository.save(groups);
            }catch (Exception e){
              throw e;
        }
           }
    }
}
