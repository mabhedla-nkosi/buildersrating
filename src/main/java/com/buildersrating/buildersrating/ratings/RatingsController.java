package com.buildersrating.buildersrating.ratings;

import com.buildersrating.buildersrating.businesstypes.BusinessTypes;
import com.buildersrating.buildersrating.exceptions.ApiExceptions;
import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/ratings")
public class RatingsController {

    private RatingsService ratingsService;
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    ApiExceptions apiExceptions;

    @GetMapping(path = "/listAllActiveRatings")
    public List<Ratings> viewActiveRatings(){
        return ratingsService.getActiveRatings();
    }

    @GetMapping(path = "/listAllInactiveRatings")
    public List<Ratings> viewInactiveRatings(){
        return ratingsService.getInactiveRatings();
    }

    @GetMapping(path = "/listSuspendedRatings")
    public List<Ratings> viewSuspendedRatings(){
        return ratingsService.getSuspendedRatings();
    }

    @GetMapping(path = "/listDeletedRatings")
    public List<Ratings> viewDeletedRatings(){
        return ratingsService.getDeletedRatings();
    }

    @GetMapping(path = "/listRating/{ratingId}")
    public Optional<Ratings> getRatingById(@PathVariable("ratingId") Long ratingId) {
       return ratingsService.findRatingById(ratingId);
    }

    @PostMapping(path = "/createRating/addRatings")
    public ResponseEntity<?> createRatings(@RequestBody Ratings ratings){
       ratingsService.createNewRating(ratings);
        apiExceptions=new ApiExceptions("Rating was created.",
                HttpStatus.CREATED, LocalDateTime.now(),"api/v1/users/addRatings");
        return ResponseEntity.status(HttpStatus.CREATED).body(apiExceptions);
    }

    @DeleteMapping(path = "/deleteRating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") Long ratingId){
        ratingsService.deleteRating(ratingId);
    }

    @PostMapping(path = "/suspendRating/{ratingId}")
    public ResponseEntity<?> suspendRating(@PathVariable("ratingId") Long ratingId){
        ratingsService.suspendRating(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body("The rating has been suspended.");
    }

    @PostMapping(path = "/restoreRating/{ratingId}")
    public ResponseEntity<?> restoreUser(@PathVariable("ratingId") Long ratingId){
        ratingsService.restoreRating(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body("The rating has been restored.");
    }

    @PutMapping(path = "/updateRating/{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable("ratingId") Long ratingId,
                                                @RequestBody Ratings ratings){

        ratingsService.updateRating(ratingId, ratings);
        apiExceptions=new ApiExceptions("The rating was updated.",
                HttpStatus.OK,LocalDateTime.now(),"api/v1/ratings/updateRating");
        return ResponseEntity.status(HttpStatus.OK).body(apiExceptions);
    }
}
