package com.buildersrating.buildersrating.groups;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupsRepositoryTest {

    @Autowired
    private GroupsRepository groupsRepositoryTest;

    @AfterEach
    void tearDown(){
        groupsRepositoryTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenGroupExistsByName() {
        //given
        String groupName="Sibabalo";
        Groups groupsTest=new Groups(groupName,0,0);
        groupsRepositoryTest.save(groupsTest);

        //when
        boolean existTest=groupsRepositoryTest.existsByName(groupName);

        //then
        assertThat(existTest).isTrue();
    }

    @Test
    void itShouldCheckWhenGroupDoesNotExistsByName() {
        //given
        String groupName="Sibabalo";

        //when
        boolean existTest=groupsRepositoryTest.existsByName(groupName);

        //then
        assertThat(existTest).isFalse();
    }

    @Test
    void itShouldCheckWhenThereAreActiveGroups() {
        //given
        Groups groupsTest1=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest1);
        Groups groupsTest2=new Groups("Sizanani",0,0);
        groupsRepositoryTest.save(groupsTest2);
        Groups groupsTest3=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest3);
        Groups groupsTest4=new Groups("UKZN",1,0);
        groupsRepositoryTest.save(groupsTest4);
        Groups groupsTest5=new Groups("Durban University of Tech",0,1);
        groupsRepositoryTest.save(groupsTest5);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllActiveGroups();

        //then
        assertThat(groupsTestAll).isNotEmpty();
    }

    @Test
    void itShouldWhenThereArentActiveGroups() {
        //given
        Groups groupsTest4=new Groups("UKZN",1,0);
        groupsRepositoryTest.save(groupsTest4);
        Groups groupsTest5=new Groups("Durban University of Tech",0,1);
        groupsRepositoryTest.save(groupsTest5);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllActiveGroups();

        //then
        assertThat(groupsTestAll).isEmpty();
    }

    @Test
    void itShouldCheckWhenThereAreInactiveGroups() {
        //given
        Groups groupsTest1=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest1);
        Groups groupsTest2=new Groups("Sizanani",0,0);
        groupsRepositoryTest.save(groupsTest2);
        Groups groupsTest3=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest3);
        Groups groupsTest4=new Groups("UKZN",1,0);
        groupsRepositoryTest.save(groupsTest4);
        Groups groupsTest5=new Groups("Durban University of Tech",0,1);
        groupsRepositoryTest.save(groupsTest5);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllInactiveGroups();

        //then
        assertThat(groupsTestAll).isNotEmpty();
    }

    @Test
    void itShouldWhenThereArentInactiveGroups() {
        //given
        Groups groupsTest1=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest1);
        Groups groupsTest2=new Groups("Sizanani",0,0);
        groupsRepositoryTest.save(groupsTest2);
        Groups groupsTest3=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest3);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllInactiveGroups();

        //then
        assertThat(groupsTestAll).isEmpty();
    }

    @Test
    void itShouldWhenThereAreDeletedGroups() {
        //given
        Groups groupsTest4=new Groups("UKZN",1,0);
        groupsRepositoryTest.save(groupsTest4);
        Groups groupsTest5=new Groups("Durban University of Tech",0,1);
        groupsRepositoryTest.save(groupsTest5);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllDeletedGroups();

        //then
        assertThat(groupsTestAll).isNotEmpty();
    }

    @Test
    void itShouldWhenThereArentDeletedGroups() {
        //given
        Groups groupsTest1=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest1);
        Groups groupsTest2=new Groups("Sizanani",0,0);
        groupsRepositoryTest.save(groupsTest2);
        Groups groupsTest3=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest3);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllDeletedGroups();

        //then
        assertThat(groupsTestAll).isEmpty();
    }

    @Test
    void itShouldCheckWhenThereAreSuspendedGroups() {
        //given
        Groups groupsTest4=new Groups("UKZN",1,0);
        groupsRepositoryTest.save(groupsTest4);
        Groups groupsTest5=new Groups("Durban University of Tech",0,1);
        groupsRepositoryTest.save(groupsTest5);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllSuspendedGroups();

        //then
        assertThat(groupsTestAll).isNotEmpty();
    }

    @Test
    void itShouldCheckWhenThereArentSuspendedGroups() {
        //given
        Groups groupsTest1=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest1);
        Groups groupsTest2=new Groups("Sizanani",0,0);
        groupsRepositoryTest.save(groupsTest2);
        Groups groupsTest3=new Groups("Thenjwayo",0,0);
        groupsRepositoryTest.save(groupsTest3);

        //when
        List<Groups> groupsTestAll=groupsRepositoryTest.findAllSuspendedGroups();

        //then
        assertThat(groupsTestAll).isEmpty();
    }
}