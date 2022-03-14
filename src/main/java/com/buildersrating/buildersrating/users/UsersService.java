package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.security.ZamamboSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UsersService {
    private static final Logger LOG = Logger.getLogger(UsersService.class.getName());

    private UsersRepository usersRepository;
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    Users usersVariable;
    public Users checkUserId(Long userId){
        usersVariable=usersRepository.findById(userId)
                .orElseThrow(()->new IllegalStateException("User with id "+userId+" does not exist."));
        return usersVariable;
    }

    public List<Users> getActiveUsers() {
        try{
            return usersRepository.findAllActiveUsers();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Users> getInactiveUsers() {
        try{
            return usersRepository.findAllInactiveUsers();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Users> getDeletedUsers() {
        try{
            return usersRepository.findAllDeletedUsers();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Users> getSuspendedUsers() {
        try{
            return usersRepository.findAllSuspendedUsers();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public Optional<Users> findUserById(Long userId) {
        try{
            return usersRepository.findById(userId);
        }catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Transactional
    public void addNewUser(Users users) {
        boolean testEmail=usersRepository.existsByEmailAddress(users.getEmailAddress());
        boolean testIdNumber=usersRepository.existsByIdNumber(users.getIdNumber());
        if(testIdNumber) {
            throw new ApiRequestException("The ID number "+users.getIdNumber()+" exists already. Please enter a different ID number.");
        }else if(users.getFirstName().length()<3){
            throw new ApiRequestException("The name is too short. Please enter a proper name.");
        }else if(users.getLastName().length()<3){
            throw new ApiRequestException("The name is too short. Please enter a proper name.");
        }else if(users.getIdNumber().length()<13||users.getIdNumber().length()>13){
            throw new ApiRequestException("The ID number is incorrect. Please enter 13 digits");
        }else if(testEmail){
            throw new ApiRequestException("Email address "+users.getEmailAddress()+" exists. Please enter a different email address.");
        }
        try{
            users.setPassword(ZamamboSecurity.encrypt(users.getPassword()));
            usersRepository.save(users);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void deleteUser(Long userId) {
        usersVariable=checkUserId(userId);
        try{
            usersVariable.setDeleted(1);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void suspendUser(Long userId) {
        usersVariable=checkUserId(userId);
        try{
            usersVariable.setSuspended(1);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void restoreUser(Long userId) {
        usersVariable=checkUserId(userId);
        try{
            usersVariable.setSuspended(0);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateUser(Long userId, Users user) {
        //checks if the object is not null and whether ratingId matches ratings.getRatingId
        if(!user.equals(null)&&user.getUserId().equals(userId)){
            usersVariable=checkUserId(userId);
        }else throw new ApiRequestException("The object is empty or user id's don't match.");

       //other validations
       if(user.getFirstName()==null && user.getFirstName().length()<=0){
            throw new ApiRequestException("The first name is not valid, please insert a proper first name.");
        }else if(user.getLastName()==null && user.getLastName().length()<=0){
            throw new ApiRequestException("The last name is not valid, please insert a proper last name.");
        }else if(user.getPassword()==null && user.getPassword().length()<=0){//more conditions needed
            throw new ApiRequestException("The password is not valid, please insert a proper password.");
        }else if(user.getEmailAddress()==null && user.getEmailAddress().length()<=0){//more conditions needed
            throw new ApiRequestException("The email address is not valid, please insert a proper email address.");
        }else if(user.getUserStatus()==null && user.getUserStatus().length()<=0){//only the admin can change this status
            throw new ApiRequestException("The last name is not valid, please insert a proper name.");
        }

       //updating the record
        try{
            usersRepository.save(user);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void changeEmailAddress(String oldEmailAddress, String newEmailAddress) {
        boolean existByEmail1=usersRepository.existsByEmailAddress(oldEmailAddress);
        boolean existByEmail2=usersRepository.existsByEmailAddress(newEmailAddress);
        if(!existByEmail1){
            throw new ApiRequestException("User with email address "+oldEmailAddress+" does not exist.");
        }else if(existByEmail2){
            throw new ApiRequestException("User with email address "+newEmailAddress+
                    " exist. You can't use an existing email address.");
        }
        try {
            usersVariable.setEmailAddress(newEmailAddress);
        } catch (IllegalArgumentException e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public Users login(Users users) throws Exception {

        String correlationId = UUID.randomUUID().toString().toUpperCase();
        LOG.info(correlationId + " start with login.");
        if (users == null) {
            throw new Exception("No information provided");
        }

        Users savedUser = usersRepository.findByEmail(users.getEmailAddress());

        if(savedUser == null) {
            throw new Exception("User with email " + users.getEmailAddress() + " doesn't exists.");
        }

        String savedPassword = ZamamboSecurity.decrypt(savedUser.getPassword());

        LOG.info(savedPassword);
        LOG.info(users.toString());
        LOG.info(savedUser.toString());

        if(users.getPassword() == null || !savedPassword.equals(users.getPassword())) {
            throw new Exception("Incorrect password.");
        }/**else {
         //setting up roles
         List<String> roles = new ArrayList<>();
         roles.add("user");
         return savedUser;
         }*/
        return savedUser;
    }
}
