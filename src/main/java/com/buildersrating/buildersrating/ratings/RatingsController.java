package com.buildersrating.buildersrating.ratings;

import com.buildersrating.buildersrating.businesstypes.BusinessTypes;
import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ratings")
public class RatingsController {

    @Autowired
    RatingsService ratingsService;

    ApiExceptions apiExceptions;

    @GetMapping(path = "/list-all-ratings")
    public List<Ratings> viewRatings(){
        try{
            return ratingsService.getRatings();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @PostMapping(path = "/addRatings")
    public ResponseEntity<?> addRatings(@RequestBody Ratings ratings){
        if(ratings.getRatingNumber()==0){
            throw new ApiRequestException("There is no ratings. Please add rating.");
        }else if(ratings.getReviewComment().length()<3){//further validation is needed here
            throw new ApiRequestException("The business is null. Please enter a business type.");
        }

        try{
            ratingsService.addNewRating(ratings);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        apiExceptions=new ApiExceptions("Rating was created.",
                HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/addRatings");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path = "{ratingId}")
    public void deleteRating(@PathVariable("ratingId") Long ratingId){
        try{
            ratingsService.deleteRating(ratingId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping(path = "{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable("ratingId") Long ratingId,
                                                @RequestParam(required = false)int ratingNumber,
                                                @RequestParam(required = false)String reviewComment,
                                          @RequestParam(required = false)Long userId,
                                          @RequestParam(required = false)Long businessId){

        try{
            ratingsService.updateRating(ratingId, ratingNumber,reviewComment,userId,businessId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        apiExceptions=new ApiExceptions("The rating was updated.",
                HttpStatus.OK,LocalDateTime.now(),"api/v1/ratings");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }
}
