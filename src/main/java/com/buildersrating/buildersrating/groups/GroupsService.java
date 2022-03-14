package com.buildersrating.buildersrating.groups;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.ratings.Ratings;
import com.buildersrating.buildersrating.users.Users;
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

    private GroupsRepository groupsRepository;
    public GroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    Groups groupsVariable;
    public Groups checkGroupId(Long groupId){
        groupsVariable=groupsRepository.findById(groupId)
                .orElseThrow(()->new IllegalStateException("Group with id "+groupId+" does not exist."));
        return groupsVariable;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Groups> getActiveGroups(){
        try
        {
            return groupsRepository.findAllActiveGroups();
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Groups> getInactiveGroups(){
        try
        {
            return groupsRepository.findAllInactiveGroups();
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Groups> getDeletedGroups(){
        try
        {
            return groupsRepository.findAllDeletedGroups();
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Groups> getSuspendedGroups(){
        try
        {
            return groupsRepository.findAllSuspendedGroups();
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void addNewGroup(Groups groups){
        //validations
        if(groups.equals(null)){
            throw new ApiRequestException("The object is empty.");
        }else if(groups.getGroupName().isEmpty()){
            throw new ApiRequestException("There is no value.");
        }else if(groups.getGroupName().length()<2){ //not a correct validation
            throw new ApiRequestException("Your name is too short.");
        }if(groupsRepository.existsByName(groups.getGroupName())) {
            throw new ApiRequestException("The group name exists already.");
        }

        //saving a record
        try{
            groupsRepository.save(groups);
        }catch (IllegalArgumentException e){
              throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Transactional
    public void deleteGroups(Long groupId) {
        groupsVariable=checkGroupId(groupId);
        try{
            groupsVariable.setDeleted(1);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @Transactional
    public void suspendGroup(Long groupId) {
        groupsVariable=checkGroupId(groupId);
        try{
            groupsVariable.setSuspended(1);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void restoreGroup(Long ratingId) {
        groupsVariable=checkGroupId(ratingId);
        try{
            groupsVariable.setSuspended(0);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateGroups(Long groupId, Groups groups) {
        groupsVariable=groupsRepository.getById(groupId);

        //checks if the object is not null and whether ratingId matches ratings.getRatingId
        if(!groups.equals(null)&&groups.getGroupId().equals(groupId)){
            groupsVariable=checkGroupId(groupId);
        }else throw new ApiRequestException("The object is empty or user id's don't match.");

        //other validations
        if(groups.getGroupName()==null && groups.getGroupName().length()<=0){
            throw new ApiRequestException("The group name is not valid, please insert a proper name.");
        }else if(groupsVariable.getGroupName().equals(groups.getGroupName())){
            throw new ApiRequestException("The group name exists.");
        }

        //updating a record
        try{
            groupsRepository.save(groups);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public Optional<Groups> findGroupById(Long groupId) {
        try{
            return groupsRepository.findById(groupId);
        }catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
