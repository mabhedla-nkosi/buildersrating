package com.buildersrating.buildersrating.business;

import com.buildersrating.buildersrating.businesstypes.BusinessTypes;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusinessService {

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    private BusinessRepository businessRepository;

    Business businessVariable;
    public Business checkBId(Long bId){
        businessVariable=businessRepository.findById(bId)
                .orElseThrow(()->new IllegalStateException("Business with id "+bId+" does not exist."));
        return businessVariable;
    }

    public List<Business> getAllActiveBusiness() {
        try{
            return businessRepository.findAllActive();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Business> getAllInactiveBusiness() {
        try{
            return businessRepository.findAllInactive();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Business> getAllDeletedBusiness() {
        try{
            return businessRepository.findAllDeleted();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Business> getAllSuspendedBusiness() {
        try{
            return businessRepository.findAllSuspended();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewBusiness(Business business) {
        if(businessRepository.existsByRegNumber(business.getbRegNumber())) {
            throw new ApiRequestException("The registration number exists already. Please enter a different registration number.");
        }

        //check
        if(business.getBusinessName().length()<3){
            throw new ApiRequestException("The business name is too short. Please enter a proper name.");
        }else if(business.getbAddress().length()<3){
            throw new ApiRequestException("The address is too short. Please enter a proper address.");
        }else if(business.getbRegNumber().length()<3){
            throw new ApiRequestException("The registration number is incorrect.");
        }

        try{
            businessRepository.save(business);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void deleteBusiness(Long businessId) {
        businessVariable=checkBId(businessId);
        try{
            businessVariable.setDeleted(1);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void suspendBusiness(Long bId) {
        businessVariable=checkBId(bId);
        try{
            businessVariable.setSuspended(1);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void restoreBusiness(Long bId) {
        businessVariable=checkBId(bId);
        try{
            businessVariable.setSuspended(0);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateBusiness(Long businessId, Business business) {
        businessVariable=checkBId(businessId);
        if(business.getBusinessName()==null){
            throw new ApiRequestException("Business name can't be empty. Please enter business name.");
        }else if(business.getbRegNumber()==null){
            throw new ApiRequestException("Registration number can't be empty. Please enter registration number");
        }else if(business.getbType()==0){
            throw new ApiRequestException("Business type can't be empty.");
        }else if(business.getbAddress()==null){
            throw new ApiRequestException("Address can't be empty. Please enter the address.");
        }else if(business.getUserId()==0){
            throw new ApiRequestException("User id can't be empty");
        }

        try {
            businessRepository.save(business);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }

}
