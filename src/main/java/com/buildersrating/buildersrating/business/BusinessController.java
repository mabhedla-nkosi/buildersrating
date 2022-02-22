package com.buildersrating.buildersrating.business;

import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    ApiExceptions apiExceptions;

    @GetMapping(path = "/get-all-business")
    public List<Business> viewBusiness(){
        try{
            return businessService.getBusiness();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/registerBusiness")
    public ResponseEntity<?> registerBusiness(@RequestBody Business business){
        if(business.getBusinessName().length()<3){
            throw new ApiRequestException("The business name is too short. Please enter a proper name.");
        }else if(business.getbAddress().length()<3){
            throw new ApiRequestException("The address is too short. Please enter a proper address.");
        }else if(business.getbRegNumber().length()<3){
            throw new ApiRequestException("The registration number is incorrect.");
        }

        try{
            businessService.addNewBusiness(business);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        apiExceptions=new ApiExceptions("The business "+business.getBusinessName()+" and the registration number "+business.getbRegNumber()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/registerBusiness");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path="{businessId}")
    public void deleteBusiness(@PathVariable("businessId") Long businessId){
        try{
            businessService.deleteBusiness(businessId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping(path = "{businessId}")
    public ResponseEntity<?> updateUser(@PathVariable("businessId") Long businessId,
                                        @RequestParam(required = false)String businessName,
                                        @RequestParam(required = false)int bType,
                                        @RequestParam(required = false)String bRegNumber,
                                        @RequestParam(required = false)String bAddress,
                                        @RequestParam(required = false)String approvalStatus){

        try{
            businessService.updateBusiness(businessId,businessName,bType,bRegNumber,bAddress,approvalStatus);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The business id "+businessId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/business");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }

}
