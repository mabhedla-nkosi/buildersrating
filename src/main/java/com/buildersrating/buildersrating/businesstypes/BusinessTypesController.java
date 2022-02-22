package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.business.Business;
import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/business-type")
public class BusinessTypesController {

    @Autowired
    BusinessTypesService businessTypesService;

    ApiExceptions apiExceptions;

    @GetMapping(path = "/get-all-types")
    public List<BusinessTypes> getAllBTypes()
    {
        try{
            return businessTypesService.getBusinessTypes();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/addBusinessType")
    public ResponseEntity<?> addBusinessType(@RequestBody BusinessTypes businessTypes){
        if(businessTypes.getBusinessType().length()<3){
            throw new ApiRequestException("The business type is too short. Please enter a proper business type.");
        }else if(businessTypes.getBusinessId()==null){
            throw new ApiRequestException("The business is null. Please enter a business type.");
        }

        try{
            businessTypesService.addNewBusinessType(businessTypes);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        apiExceptions=new ApiExceptions("The business type "+businessTypes.getBusinessType()+" and the business number "+businessTypes.getBusinessId()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/addBusinessType");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path="{bTypeId}")
    public void deleteBusinessType(@PathVariable("bTypeId") Long bTypeId){
        try{
            businessTypesService.deleteBusinessType(bTypeId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping(path = "{bTypeId}")
    public ResponseEntity<?> updateBusinessType(@PathVariable("bTypeId") Long bTypeId,
                                          @RequestParam(required = false)String businessType,
                                          @RequestParam(required = false)Long businessId){

        try{
            businessTypesService.updateBusinessType(bTypeId,businessType,businessId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The businesss type "+businessType
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/business-type");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }
}
