package com.buildersrating.buildersrating.ratings;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    RatingsRepository ratingsRepository;

    public List<Ratings> getRatings() {
        try{
            return ratingsRepository.findAll();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewRating(Ratings ratings) {
        if(ratings.getRatingNumber()==0) {
            throw new ApiRequestException("The ratings has not added. Please add rating.");
        }else if(ratings.getReviewComment()==null){//Should we mandate this?
            throw new ApiRequestException("There is no review. Please enter a review.");
        }else if(ratings.getUserId()==null){
            throw new ApiRequestException("There is no user id.");
        }else if(ratings.getBusinessId()==null){
            throw new ApiRequestException("There is no business id. Please choose a business.");
        }

        try{
            ratingsRepository.save(ratings);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void deleteRating(Long ratingId) {
        boolean exists=ratingsRepository.existsById(ratingId);
        if(!exists){
            throw new ApiRequestException("Rating with id "+ratingId+" does not exist.");
        }
        try{
            ratingsRepository.deleteById(ratingId);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public void updateRating(Long ratingId, int ratingNumber, String reviewComment, Long userId, Long businessId) {
        //this method is not written yet
    }
}
