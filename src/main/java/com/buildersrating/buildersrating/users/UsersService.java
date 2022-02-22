package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    Users usersVariable;

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
        boolean exists=usersRepository.existsById(userId);
        if(!exists){
            throw new ApiRequestException("User with id "+userId+" does not exist.");
        }
        try{
            usersRepository.deleteById(userId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void updateUser(Long userId, String firstName, String lastName, String password, String emailAddress, String userStatus, Long id) {
        boolean exists=usersRepository.existsById(userId);
        usersVariable=usersRepository.getById(userId);
        if(!exists){
            throw new ApiRequestException("User with id "+userId+" does not exist.");
        }else if(firstName==null && firstName.length()<=0){
            throw new ApiRequestException("The first name is not valid, please insert a proper first name.");
        }else if(lastName==null && lastName.length()<=0){
            throw new ApiRequestException("The last name is not valid, please insert a proper last name.");
        }else if(password==null && password.length()<=0){//more conditions needed
            throw new ApiRequestException("The password is not valid, please insert a proper password.");
        }else if(emailAddress==null && emailAddress.length()<=0){//more conditions needed
            throw new ApiRequestException("The email address is not valid, please insert a proper email address.");
        }else if(userStatus==null && userStatus.length()<=0){//only the admin can change this status
            throw new ApiRequestException("The last name is not valid, please insert a proper name.");
        }

        try{
            usersVariable.setFirstName(firstName);//struggling here
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
