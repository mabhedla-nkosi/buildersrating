package com.buildersrating.buildersrating.business;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    public List<Business> getBusiness() {
        try{
            return businessRepository.findAll();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewBusiness(Business business) {
        if(businessRepository.existsByRegNumber(business.getbRegNumber())) {
            throw new ApiRequestException("The registration number exists already. Please enter a different registration number.");
        }
        try{
            businessRepository.save(business);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void deleteBusiness(Long businessId) {
        boolean exists=businessRepository.existsById(businessId);
        if(!exists){
            throw new ApiRequestException("Business with id "+businessId+" does not exist.");
        }
        try{
            businessRepository.deleteById(businessId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void updateBusiness(Long businessId, String businessName, int bType, String bRegNumber, String bAddress, String approvalStatus) {
    }

    //update method is lacking
}
