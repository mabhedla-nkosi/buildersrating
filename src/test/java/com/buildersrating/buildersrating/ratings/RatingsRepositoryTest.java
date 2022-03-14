package com.buildersrating.buildersrating.ratings;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class RatingsRepositoryTest {
    @Autowired
    RatingsRepository ratingsRepositoryTest;

    @AfterEach
    void tearDown(){
        ratingsRepositoryTest.deleteAll();
    }

    @Test
    void checkWhenThereAreActiveRatings() {
        //given
        Ratings ratingsTest1=new Ratings(2,"Very good service",2L,1L,0,0);
        ratingsRepositoryTest.save(ratingsTest1);
        Ratings ratingsTest2=new Ratings(3,"Very bad service",3L,1L,1,0);
        ratingsRepositoryTest.save(ratingsTest2);
        Ratings ratingsTest3=new Ratings(4,"Very good service",2L,1L,0,1);
        ratingsRepositoryTest.save(ratingsTest3);

        //when
        List<Ratings> getActiveRatings=ratingsRepositoryTest.findAllActiveRating();

        //then
        assertThat(getActiveRatings).isNotEmpty();
    }

    @Test
    void checkWhenThereArentActiveRatings() {
        //given
        Ratings ratingsTest2=new Ratings(3,"Very bad service",3L,1L,1,0);
        ratingsRepositoryTest.save(ratingsTest2);
        Ratings ratingsTest3=new Ratings(4,"Very good service",2L,1L,0,1);
        ratingsRepositoryTest.save(ratingsTest3);

        //when
        List<Ratings> getActiveRatings=ratingsRepositoryTest.findAllActiveRating();

        //then
        assertThat(getActiveRatings).isEmpty();
    }

    @Test
    void checkWhenThereAreInactiveRatings() {
        //given
        Ratings ratingsTest2=new Ratings(3,"Very bad service",3L,1L,1,0);
        ratingsRepositoryTest.save(ratingsTest2);
        Ratings ratingsTest3=new Ratings(4,"Very good service",2L,1L,0,1);
        ratingsRepositoryTest.save(ratingsTest3);

        //when
        List<Ratings> getInactiveRatings=ratingsRepositoryTest.findAllInactiveRating();

        //then
        assertThat(getInactiveRatings).isNotEmpty();
    }

    @Test
    void checkWhenThereArentInactiveRatings() {
        //given
        Ratings ratingsTest1=new Ratings(2,"Very good service",2L,1L,0,0);
        ratingsRepositoryTest.save(ratingsTest1);

        //when
        List<Ratings> getInactiveRatings=ratingsRepositoryTest.findAllInactiveRating();

        //then
        assertThat(getInactiveRatings).isEmpty();
    }

    @Test
    void checkWhenThereAreDeletedRatings() {
        //given
        Ratings ratingsTest2=new Ratings(3,"Very bad service",3L,1L,1,0);
        ratingsRepositoryTest.save(ratingsTest2);

        //when
        List<Ratings> getDeletedRatings=ratingsRepositoryTest.findAllDeletedRating();

        //then
        assertThat(getDeletedRatings).isNotEmpty();
    }

    @Test
    void checkWhenThereArentDeletedRatings() {
        //given
        Ratings ratingsTest2=new Ratings(3,"Very bad service",3L,1L,0,0);
        ratingsRepositoryTest.save(ratingsTest2);

        //when
        List<Ratings> getDeletedRatings=ratingsRepositoryTest.findAllDeletedRating();

        //then
        assertThat(getDeletedRatings).isEmpty();
    }

    @Test
    void checkWhenThereAreSuspendedRatings() {
        //given
        Ratings ratingsTest3=new Ratings(4,"Very good service",2L,1L,0,1);
        ratingsRepositoryTest.save(ratingsTest3);

        //when
        List<Ratings> getSuspendedRatings=ratingsRepositoryTest.findAllSuspendedRating();

        //then
        assertThat(getSuspendedRatings).isNotEmpty();
    }

    @Test
    void checkWhenThereArentSuspendedRatings() {
        //given
        Ratings ratingsTest3=new Ratings(4,"Very good service",2L,1L,0,0);
        ratingsRepositoryTest.save(ratingsTest3);

        //when
        List<Ratings> getSuspendedRatings=ratingsRepositoryTest.findAllSuspendedRating();

        //then
        assertThat(getSuspendedRatings).isEmpty();
    }
}