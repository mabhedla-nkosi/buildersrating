package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessTypesService {

    @Autowired
    BusinessTypesRepository businessTypesRepository;

    public List<BusinessTypes> getBusinessTypes() {
        try{
            return businessTypesRepository.findAll();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewBusinessType(BusinessTypes businessTypes) {
        try{
            businessTypesRepository.save(businessTypes);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void deleteBusinessType(Long bTypeId) {
        boolean exists=businessTypesRepository.existsById(bTypeId);
        if(!exists){
            throw new ApiRequestException("Business type with id "+bTypeId+" does not exist.");
        }
        try{
            businessTypesRepository.deleteById(bTypeId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void updateBusinessType(Long bTypeId, String businessType, Long businessId) {
        //Update method needs to be written
    }
}
