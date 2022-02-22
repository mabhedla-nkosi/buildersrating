package com.buildersrating.buildersrating.groups;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@ResponseBody
public class GroupsService {

    @Autowired
    GroupsRepository groupsRepository;

    Groups groupsVariable;

    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Groups> getGroups(){
        try
        {
            return groupsRepository.findAll();
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void addNewGroup(Groups groups){
        if(groupsRepository.existsByName(groups.getGroupName())) {
            throw new ApiRequestException("The group name exists already.");
        //    throw new IllegalStateException()
        }
        try{
            groupsRepository.save(groups);
        }catch (IllegalArgumentException e){
              throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    public void deleteGroups(Long groupId) {
        boolean exists=groupsRepository.existsById(groupId);
        if(!exists){
            throw new ApiRequestException("Group with id "+groupId+" does not exist.");
        }
        try{
            groupsRepository.deleteById(groupId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Transactional
    public void updateGroups(Long groupId, String groupName) {
        boolean exists=groupsRepository.existsById(groupId);
        groupsVariable=groupsRepository.getById(groupId);
        if(!exists){
            throw new ApiRequestException("Group with id "+groupId+" does not exist.");
        }else if(groupName==null && groupName.length()<=0){
            throw new ApiRequestException("The group name is not valid, please insert a proper name.");
        }else if(groupsVariable.getGroupName().equals(groupName)){
            throw new ApiRequestException("The group name is the same.");
        }

        try{
            groupsVariable.setGroup_name(groupName);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
