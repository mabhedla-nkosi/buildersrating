package com.buildersrating.buildersrating.users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepositoryTest;

    @AfterEach
    void tearDown(){
        usersRepositoryTest.deleteAll();
    }

    @Test
    void checkWhenRecordExistsByIdNumber() {
        //given
        String idNumberTest="9109347830893";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                idNumberTest, "thabiso@gmail.com", "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);

        //when
        boolean userIdTest=usersRepositoryTest.existsByIdNumber(idNumberTest);

        //then
        assertThat(userIdTest).isTrue();
    }

    @Test
    void checkWhenRecordDoesntExistsByIdNumber() {
        //given
        String idNumberTest="9109347830893";

        //when
        boolean userIdTest=usersRepositoryTest.existsByIdNumber(idNumberTest);

        //then
        assertThat(userIdTest).isFalse();
    }

    @Test
    void checkWhenRecordExistsByEmailAddress() {
        //given
        String emailAddressTest="thabiso@gmail.com";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", emailAddressTest, "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);

        //when
        boolean userEmailTest=usersRepositoryTest.existsByEmailAddress(emailAddressTest);
        //System.out.println(userEmailTest);

        //then
        assertThat(userEmailTest).isTrue();
    }

    @Test
    void checkWhenRecordDoesntExistsByEmailAddress() {
        //given
        String emailAddressTest="thabiso@gmail.com";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "emailAddressTest", "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);

        //when
        boolean userEmailTest=usersRepositoryTest.existsByEmailAddress(emailAddressTest);

        //then
        assertThat(userEmailTest).isFalse();
    }

    @Test
    void checkWhenThereAreActiveUsers() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
        0, 0);
        usersRepositoryTest.save(userTest1);
        Users userTest2=new Users("Thokozani", "Khumalo","12356",
                "9309347830893", "thokozani@gmail.com", "business man", 2L,
                0, 0);
        usersRepositoryTest.save(userTest2);
        Users userTest3=new Users("Sazi", "Ndlovu","12356",
                "9409347830893", "sazi@gmail.com", "business man", 1L,
                1, 0);
        usersRepositoryTest.save(userTest3);
        Users userTest4=new Users("Lala", "Shange","12356",
                "9609347830893", "lala@gmail.com", "business man", 2L,
                0, 1);
        usersRepositoryTest.save(userTest4);

        //when
        List<Users> userTestAllActive=usersRepositoryTest.findAllActiveUsers();

        //then
        assertThat(userTestAllActive).isNotEmpty();
    }

    @Test
    void checkWhenThereArentActiveUsers() {
        //given
        Users userTest3=new Users("Sazi", "Ndlovu","12356",
                "9409347830893", "sazi@gmail.com", "business man", 1L,
                1, 0);
        usersRepositoryTest.save(userTest3);
        Users userTest4=new Users("Lala", "Shange","12356",
                "9609347830893", "lala@gmail.com", "business man", 2L,
                0, 1);
        usersRepositoryTest.save(userTest4);

        //when
        List<Users> userTestAllActive=usersRepositoryTest.findAllActiveUsers();

        //then
        assertThat(userTestAllActive).isEmpty();
    }

    @Test
    void checkWhenThereAreInactiveUsers() {
        //given
        Users userTest3=new Users("Sazi", "Ndlovu","12356",
                "9409347830893", "sazi@gmail.com", "business man", 1L,
                1, 0);
        usersRepositoryTest.save(userTest3);
        Users userTest4=new Users("Lala", "Shange","12356",
                "9609347830893", "lala@gmail.com", "business man", 2L,
                0, 1);
        usersRepositoryTest.save(userTest4);

        //when
        List<Users> userTestAllInactive=usersRepositoryTest.findAllInactiveUsers();

        //then
        assertThat(userTestAllInactive).isNotEmpty();
    }

    @Test
    void checkWhenThereArentInactiveUsers() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);
        Users userTest2=new Users("Thokozani", "Khumalo","12356",
                "9309347830893", "thokozani@gmail.com", "business man", 2L,
                0, 0);
        usersRepositoryTest.save(userTest2);

        //when
        List<Users> userTestAllInactive=usersRepositoryTest.findAllInactiveUsers();

        //then
        assertThat(userTestAllInactive).isEmpty();
    }

    @Test
    void checkWhenThereAreDeletedUsers() {
        //given
        Users userTest3=new Users("Sazi", "Ndlovu","12356",
                "9409347830893", "sazi@gmail.com", "business man", 1L,
                1, 0);
        usersRepositoryTest.save(userTest3);
        Users userTest4=new Users("Lala", "Shange","12356",
                "9609347830893", "lala@gmail.com", "business man", 2L,
                0, 1);
        usersRepositoryTest.save(userTest4);

        //when
        List<Users> userTestAllDeleted=usersRepositoryTest.findAllDeletedUsers();

        //then
        assertThat(userTestAllDeleted).isNotEmpty();
    }

    @Test
    void checkWhenThereArentDeletedUsers() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);
        Users userTest2=new Users("Thokozani", "Khumalo","12356",
                "9309347830893", "thokozani@gmail.com", "business man", 2L,
                0, 0);
        usersRepositoryTest.save(userTest2);

        //when
        List<Users> userTestAllDeleted=usersRepositoryTest.findAllDeletedUsers();

        //then
        assertThat(userTestAllDeleted).isEmpty();
    }

    @Test
    void checkWhenThereAreSuspendedUsers() {
        //given
        Users userTest3=new Users("Sazi", "Ndlovu","12356",
                "9409347830893", "sazi@gmail.com", "business man", 1L,
                1, 0);
        usersRepositoryTest.save(userTest3);
        Users userTest4=new Users("Lala", "Shange","12356",
                "9609347830893", "lala@gmail.com", "business man", 2L,
                0, 1);
        usersRepositoryTest.save(userTest4);

        //when
        List<Users> userTestAllSuspended=usersRepositoryTest.findAllSuspendedUsers();

        //then
        assertThat(userTestAllSuspended).isNotEmpty();
    }

    @Test
    void checkWhenThereArentSuspendedUsers() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);
        Users userTest2=new Users("Thokozani", "Khumalo","12356",
                "9309347830893", "thokozani@gmail.com", "business man", 2L,
                0, 0);
        usersRepositoryTest.save(userTest2);

        //when
        List<Users> userTestAllSuspended=usersRepositoryTest.findAllSuspendedUsers();

        //then
        assertThat(userTestAllSuspended).isEmpty();
    }

    @Test
    void checkWhenEmailExist(){
        //given
        String emailAddress="thabiso@gmail.com";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893",emailAddress , "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);

        //when
        Users usersEmailAddressTest=usersRepositoryTest.findByEmail(emailAddress);

        //then
        assertThat(usersEmailAddressTest).isNotNull();
    }

    @Test
    void checkWhenEmailDoesntExist(){
        //given
        String emailAddress="thabiso@gmail.com";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893","email@Address" , "business man", 1L,
                0, 0);
        usersRepositoryTest.save(userTest1);

        //when
        Users usersEmailAddressTest=usersRepositoryTest.findByEmail(emailAddress);

        //then
        assertThat(usersEmailAddressTest).isNull();
    }
}