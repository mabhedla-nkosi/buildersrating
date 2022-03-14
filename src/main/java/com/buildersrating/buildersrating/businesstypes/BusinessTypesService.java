package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessTypesService {

    private BusinessTypesRepository businessTypesRepository;
    public BusinessTypesService(BusinessTypesRepository businessTypesRepository) {
        this.businessTypesRepository = businessTypesRepository;
    }

    BusinessTypes businessTypesVariable;
    public BusinessTypes checkBTypeId(Long bTypeId){
        businessTypesVariable=businessTypesRepository.findById(bTypeId)
                .orElseThrow(()->new IllegalStateException("Business Type with id "+bTypeId+" does not exist."));
        return businessTypesVariable;
    }

    public List<BusinessTypes> getActiveBusinessTypes() {
        try{
            return businessTypesRepository.findAllActiveBTypes();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<BusinessTypes> getInactiveBusinessTypes() {
        try{
            return businessTypesRepository.findAllInactiveBTypes();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<BusinessTypes> getDeletedBusinessTypes() {
        try{
            return businessTypesRepository.findAllDeletedBTypes();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<BusinessTypes> getSuspendedBusinessTypes() {
        try{
            return businessTypesRepository.findAllSuspendedBTypes();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public Optional<BusinessTypes> findBTypeById(Long bTypeId) {
        try{
            return businessTypesRepository.findById(bTypeId);
        }catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void addNewBusinessType(BusinessTypes businessTypes) {
        if(businessTypes.getBusinessType().length()<3){
            throw new ApiRequestException("The business type is too short. Please enter a proper business type.");
        }else if(businessTypes.getBusinessId()==null){
            throw new ApiRequestException("The business is null. Please enter a business type.");
        }
        try{
            businessTypesRepository.save(businessTypes);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void deleteBusinessType(Long bTypeId) {
        try{
            businessTypesVariable.setDeleted(1);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void suspendBType(Long bTypeId) {
        businessTypesVariable=checkBTypeId(bTypeId);
        try{
            businessTypesVariable.setSuspended(1);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void restoreBType(Long bTypeId) {
        businessTypesVariable=checkBTypeId(bTypeId);
        try{
            businessTypesVariable.setSuspended(0);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateBusinessType(Long bTypeId, BusinessTypes businessTypes) {
        //checks if the object is not null and whether ratingId matches ratings.getRatingId
        if(!businessTypes.equals(null)&&businessTypes.getBusinessId().equals(bTypeId)){
            businessTypesVariable=checkBTypeId(bTypeId);
        }else throw new ApiRequestException("The object is empty or business type id's don't match.");

        //other validations
        if(businessTypes.getBusinessType()==null){
            throw new ApiRequestException("There is no business type, please select one.");
        }else if(businessTypes.getBusinessId()==0){
            throw new ApiRequestException("There is no business id, please select one.");
        }

        //updating the record
        try{
            businessTypesRepository.save(businessTypes);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


}
