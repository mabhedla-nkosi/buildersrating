package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public List<Users> getUsers() {
        try{
            return usersRepository.findAll();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewUser(Users users) {
        if(usersRepository.existsByIdNumber(users.getIdNumber())) {
            throw new ApiRequestException("The ID number exists already. Please enter a different ID number.");
        }
        try{
            usersRepository.save(users);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void deleteUser(Long userId) {
    }

    public void updateUser(Long groupId) {
    }
}
