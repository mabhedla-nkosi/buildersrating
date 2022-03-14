package com.buildersrating.buildersrating.businesstypes;

import com.buildersrating.buildersrating.groups.Groups;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BusinessTypesRepositoryTest {

    @Autowired
    BusinessTypesRepository businessTypesRepositoryTest;

    @AfterEach
    void tearDown(){
        businessTypesRepositoryTest.deleteAll();
    }

    @Disabled
    @Test
    void existsByBType() {
        //given

        //when

        //then
    }

    @Test
    void checkWhenThereAreActiveBTypes() {
        //given
        BusinessTypes businessTypesTest1=new BusinessTypes("Building",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest1);
        BusinessTypes businessTypesTest2=new BusinessTypes("Fencing",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest2);
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,1,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,0,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestActive=businessTypesRepositoryTest.findAllActiveBTypes();

        //then
        assertThat(businessTypesTestActive).isNotEmpty();
    }

    @Test
    void checkWhenThereArentActiveBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,1,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,0,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestActive=businessTypesRepositoryTest.findAllActiveBTypes();

        //then
        assertThat(businessTypesTestActive).isEmpty();
    }

    @Test
    void checkWhenThereAreInactiveBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,1,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,0,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestInactive=businessTypesRepositoryTest.findAllInactiveBTypes();

        //then
        assertThat(businessTypesTestInactive).isNotEmpty();
    }

    @Test
    void checkWhenThereArentInactiveBTypes() {
        //given
        BusinessTypes businessTypesTest1=new BusinessTypes("Building",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest1);
        BusinessTypes businessTypesTest2=new BusinessTypes("Fencing",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest2);

        //when
        List<BusinessTypes> businessTypesTestInactive=businessTypesRepositoryTest.findAllInactiveBTypes();

        //then
        assertThat(businessTypesTestInactive).isEmpty();
    }

    @Test
    void checkWhenThereAreDeletedBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,1,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,1,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestDeleted=businessTypesRepositoryTest.findAllDeletedBTypes();

        //then
        assertThat(businessTypesTestDeleted).isNotEmpty();
    }

    @Test
    void checkWhenThereArentDeletedBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,0,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestDeleted=businessTypesRepositoryTest.findAllDeletedBTypes();

        //then
        assertThat(businessTypesTestDeleted).isEmpty();
    }

    @Test
    void checkWhenThereAreSuspendedBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,0,1);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,1,1);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestSuspended=businessTypesRepositoryTest.findAllSuspendedBTypes();

        //then
        assertThat(businessTypesTestSuspended).isNotEmpty();
    }

    @Test
    void checkWhenThereArentSuspendedBTypes() {
        //given
        BusinessTypes businessTypesTest3=new BusinessTypes("Geysers",1L,0,0);
        businessTypesRepositoryTest.save(businessTypesTest3);
        BusinessTypes businessTypesTest4=new BusinessTypes("Plastering",1L,1,0);
        businessTypesRepositoryTest.save(businessTypesTest4);

        //when
        List<BusinessTypes> businessTypesTestSuspended=businessTypesRepositoryTest.findAllSuspendedBTypes();

        //then
        assertThat(businessTypesTestSuspended).isEmpty();
    }
}