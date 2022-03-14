package com.buildersrating.buildersrating.ficadocuments;

import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.ratings.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fica-documents")
public class FicaDocumentsController {

    private FicaDocumentsService ficaDocumentsService;
    public FicaDocumentsController(FicaDocumentsService ficaDocumentsService) {
        this.ficaDocumentsService = ficaDocumentsService;
    }

    ApiExceptions apiExceptions;

    @GetMapping(path = "/get-fica-documents")
    public List<FicaDocuments> viewFicaDocuments()
    {
        try{
            return ficaDocumentsService.getFicaDocuments();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }


    @PostMapping(path = "/addFicaDocuments")
    public ResponseEntity<?> addFicaDocuments(@RequestBody FicaDocuments ficaDocuments){
        //validations

        try{
            ficaDocumentsService.addNewDocuments(ficaDocuments);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        apiExceptions=new ApiExceptions("Documents were added.",
                HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/addFicaDocuments");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path = "{docId}")
    public void deleteDocuments(@PathVariable("docId") Long docId){
        try{
            ficaDocumentsService.deleteFicaDocuments(docId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping(path = "{docId}")
    public ResponseEntity<?> updateFicaDocuments(@PathVariable("docId") Long docId,
                                          @RequestParam(required = false)int ratingNumber,
                                          @RequestParam(required = false)String identityDocument,
                                          @RequestParam(required = false)Long proofOfAddress,
                                          @RequestParam(required = false)LocalDateTime expiryDate,
                                                 @RequestParam(required = false)Long businessId,
                                                 @RequestParam(required = false)LocalDateTime userId){

        try{
            ficaDocumentsService.updateFicaDocuments(docId,ratingNumber,identityDocument,proofOfAddress,expiryDate,businessId,userId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The record was updated.",
                HttpStatus.OK,LocalDateTime.now(),"api/v1/fica-documents");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }
}
