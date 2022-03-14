package com.buildersrating.buildersrating.ratings;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import com.buildersrating.buildersrating.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RatingsService {

    private RatingsRepository ratingsRepository;
    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    Ratings ratingsVariable;
    public Ratings checkRatingId(Long ratingId){
        ratingsVariable=ratingsRepository.findById(ratingId)
                .orElseThrow(()->new IllegalStateException("Rating with id "+ratingId+" does not exist."));
        return ratingsVariable;
    }

    public List<Ratings> getActiveRatings() {
        try{
            return ratingsRepository.findAllActiveRating();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Ratings> getInactiveRatings() {
        try{
            return ratingsRepository.findAllInactiveRating();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Ratings> getDeletedRatings() {
        try{
            return ratingsRepository.findAllDeletedRating();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public List<Ratings> getSuspendedRatings() {
        try{
            return ratingsRepository.findAllSuspendedRating();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public Optional<Ratings> findRatingById(Long ratingId) {
        try{
            return ratingsRepository.findById(ratingId);
        }catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void createNewRating(Ratings ratings) {
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

    @Transactional
    public void deleteRating(Long ratingId) {
        ratingsVariable=checkRatingId(ratingId);
        try{
            ratingsVariable.setDeleted(1);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Transactional
    public void suspendRating(Long ratingId) {
        ratingsVariable=checkRatingId(ratingId);
        try{
            ratingsVariable.setSuspended(1);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void restoreRating(Long ratingId) {
        ratingsVariable=checkRatingId(ratingId);
        try{
            ratingsVariable.setSuspended(0);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateRating(Long ratingId, Ratings ratings) {
        //checks if the object is not null and whether ratingId matches ratings.getRatingId
        if(!ratings.equals(null)&&ratings.getRatingId().equals(ratingId)){
            ratingsVariable=checkRatingId(ratings.getRatingId());
        }else throw new ApiRequestException("The object is empty or rating id's don't match.");

        //validations for fields
        if(ratingsVariable.getRatingNumber()==0){
            throw new ApiRequestException("The rating number can't be 0. Please select a rating.");
        }else if(ratingsVariable.getReviewComment()==null){
            throw new ApiRequestException("The review comment can't be empty. Please write a comment.");
        }else if(ratingsVariable.getReviewComment().length()<5){
            throw new ApiRequestException("The review comment must be more than 5 characters. Please write a comment.");
        }else if(ratingsVariable.getUserId()==0){
            throw new ApiRequestException("The user id can't be 0.");
        }else if(ratingsVariable.getBusinessId()==0){
            throw new ApiRequestException("The business id can't be 0.");
        }

        //updating the record
        try{
            ratingsRepository.save(ratings);
        }catch (IllegalArgumentException e){
            throw new ApiRequestException(e.getMessage());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
