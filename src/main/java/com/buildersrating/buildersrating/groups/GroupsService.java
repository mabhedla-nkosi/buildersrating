package com.buildersrating.buildersrating.groups;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@ResponseBody
public class GroupsService {

    @Autowired
    GroupsRepository groupsRepository;

    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Groups> getGroups(){
        return groupsRepository.findAll();
    }

    public void addNewGroup(Groups groups){
        if(groupsRepository.existsByName(groups.getGroupName())) {
            throw new ApiRequestException("The group name exists already.");
        }
        try{
            groupsRepository.save(groups);
        }catch (IllegalArgumentException e){
              throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    public void  addNewGroup2(Groups groups){
        if(!groupsRepository.existsByName(groups.getGroupName())){
            try{

                groupsRepository.save(groups);
                //   return HttpStatus.CREATED;
                //     @ResponseStatus(code = HttpStatus.CREATED)
             //   return ResponseEntity.status(HttpStatus.CREATED).body("The Group Record has been added");
            }catch (RuntimeException e){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }else{
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Record already exists");
        }

    }
}
