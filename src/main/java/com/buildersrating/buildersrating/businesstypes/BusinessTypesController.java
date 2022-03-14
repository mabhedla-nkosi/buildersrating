package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.business.Business;
import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.ratings.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/business-type")
public class BusinessTypesController {

    private BusinessTypesService businessTypesService;
    public BusinessTypesController(BusinessTypesService businessTypesService) {
        this.businessTypesService = businessTypesService;
    }

    ApiExceptions apiExceptions;

    @GetMapping(path = "/getAllActiveTypes")
    public List<BusinessTypes> getAllActiveBTypes()
    {
        return businessTypesService.getActiveBusinessTypes();
    }

    @GetMapping(path = "/getAllInactiveTypes")
    public List<BusinessTypes> getAllInactiveBTypes()
    {
        return businessTypesService.getInactiveBusinessTypes();
    }

    @GetMapping(path = "/getAllDeletedTypes")
    public List<BusinessTypes> getAllDeletedBTypes()
    {
        return businessTypesService.getDeletedBusinessTypes();
    }

    @GetMapping(path = "/getAllSuspendedTypes")
    public List<BusinessTypes> getAllSuspendedBTypes()
    {
        return businessTypesService.getSuspendedBusinessTypes();
    }

    @GetMapping(path = "/listBTypes/{bTypeId}")
    public Optional<BusinessTypes> getBTypeById(@PathVariable("bTypeId") Long bTypeId) {
        return businessTypesService.findBTypeById(bTypeId);
    }

    @PostMapping(path = "/addBusinessType")
    public ResponseEntity<?> addBusinessType(@RequestBody BusinessTypes businessTypes){
        businessTypesService.addNewBusinessType(businessTypes);

        apiExceptions=new ApiExceptions("The business type "+businessTypes.getBusinessType()+" and the business number "+businessTypes.getBusinessId()
                +" was created.", HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/addBusinessType");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path="/deleteBType/{bTypeId}")
    public void deleteBusinessType(@PathVariable("bTypeId") Long bTypeId){
        businessTypesService.deleteBusinessType(bTypeId);
    }

    @PostMapping(path = "suspendBType/{bTypeId}")
    public void suspendBusinessType(@PathVariable("bTypeId") Long bTypeId){
        businessTypesService.suspendBType(bTypeId);
    }

    @PostMapping(path = "restoreBType/{bTypeId}")
    public void restoreBusinessType(@PathVariable("bTypeId") Long bTypeId){
        businessTypesService.restoreBType(bTypeId);
    }

    @PutMapping(path = "/updateBType/{bTypeId}")
    public ResponseEntity<?> updateBusinessType(@PathVariable("bTypeId") Long bTypeId, @RequestBody BusinessTypes businessTypes){
        businessTypesService.updateBusinessType(bTypeId,businessTypes);
        apiExceptions=new ApiExceptions("The businesss type "+businessTypes.getBusinessType()
                +" was updated.",HttpStatus.OK,LocalDateTime.now(),"api/v1/business-type/update");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }
}
