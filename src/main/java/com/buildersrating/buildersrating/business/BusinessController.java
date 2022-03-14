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

    private BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    ApiExceptions apiExceptions;

    @GetMapping(path = "/getAllActiveBusiness")
    public List<Business> viewActiveBusiness(){
        return businessService.getAllActiveBusiness();
    }

    @GetMapping(path = "/getAllInactiveBusiness")
    public List<Business> viewInactiveBusiness(){
        return businessService.getAllInactiveBusiness();
    }

    @GetMapping(path = "/getAllDeletedBusiness")
    public List<Business> viewDeletedBusiness(){
        return businessService.getAllDeletedBusiness();
    }

    @GetMapping(path = "/getAllSuspendedBusiness")
    public List<Business> viewSuspendedBusiness(){
        return businessService.getAllSuspendedBusiness();
    }

    @PostMapping(path = "/registerBusiness")
    public ResponseEntity<?> registerBusiness(@RequestBody Business business){
        businessService.addNewBusiness(business);

        apiExceptions=new ApiExceptions("The business "+business.getBusinessName()+" and the registration number "+business.getbRegNumber()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/business/registerBusiness");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path="/deleteBusiness/{businessId}")
    public void deleteBusiness(@PathVariable("businessId") Long businessId){
        businessService.deleteBusiness(businessId);
    }

    @PostMapping(path = "/suspendBusiness/{businessId}")
    public void suspendBusiness(@PathVariable("businessId") Long businessId){
        businessService.suspendBusiness(businessId);
    }

    @PostMapping(path = "/restoreBusiness/{businessId}")
    public void restoreBusiness(@PathVariable("businessId")Long businessId){
        businessService.restoreBusiness(businessId);
    }

    @PutMapping(path = "/updateBusiness/{businessId}")
    public ResponseEntity<?> updateUser(@PathVariable("businessId") Long businessId,Business business){

        businessService.updateBusiness(businessId,business);
        apiExceptions=new ApiExceptions("The business id "+businessId
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/business/updateBusiness");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }

}
