package com.buildersrating.buildersrating.users;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class UsersServiceTest {

    @Mock private UsersRepository usersRepository;
    private UsersService usersServiceTest;
 //   private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
      //  autoCloseable=MockitoAnnotations.openMocks(this);
        usersServiceTest=new UsersService(usersRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
   //     autoCloseable.close();
    }

    @Test
    @Disabled
    void checkUserId() {
        //given

        //when

        //then
    }

    @Test
    void canGetActiveUsers() {
        //when
        usersServiceTest.getActiveUsers();

        //then
        verify(usersRepository).findAllActiveUsers();
    }

    @Test
    void getInactiveUsers() {
        //when
        usersServiceTest.getInactiveUsers();

        //then
        verify(usersRepository).findAllInactiveUsers();
    }

    @Test
    void getDeletedUsers() {
        //when
        usersServiceTest.getDeletedUsers();

        //then
        verify(usersRepository).findAllDeletedUsers();
    }

    @Test
    void getSuspendedUsers() {
        //when
        usersServiceTest.getSuspendedUsers();

        //then
        verify(usersRepository).findAllSuspendedUsers();
    }

    @Test
    @Disabled
    void findUserById() {
    }

    @Test
    void canAddNewUser() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        //when
        usersServiceTest.addNewUser(userTest1);

        //then
        ArgumentCaptor<Users> usersArgumentCaptor=ArgumentCaptor.forClass(Users.class);
        verify(usersRepository).save(usersArgumentCaptor.capture());
        Users capturedUser=usersArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(userTest1);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        given(usersRepository.existsByEmailAddress(userTest1.getEmailAddress())).willReturn(true);

        //when
        //then
        assertThatThrownBy(()-> usersServiceTest.addNewUser(userTest1)).isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Email address "+userTest1.getEmailAddress()
                        +" exists. Please enter a different email address.");
        verify(usersRepository,never()).save(any());
    }

    @Test
    void willThrowWhenIdNumberTaken() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);
        given(usersRepository.existsByIdNumber(userTest1.getIdNumber())).willReturn(true);

        //when
        //then
        assertThatThrownBy(()-> usersServiceTest.addNewUser(userTest1)).isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("The ID number "+userTest1.getIdNumber()
                        +" exists already. Please enter a different ID number.");
        verify(usersRepository,never()).save(any());
    }

    @Test
    void deleteUser() {
        //given
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);

        //when
        userTest1.setDeleted(1);

        //then
        assertTrue(userTest1.getDeleted()==1);
    }

    @Test
    void suspendUser() {
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);

        //when
        userTest1.setSuspended(1);

        //then
        assertTrue(userTest1.getSuspended()==1);
    }

    @Test
    void restoreUser() {
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);

        //when
        userTest1.setSuspended(0);

        //then
        assertTrue(userTest1.getSuspended()==0);
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    @Disabled
    void changeEmailAddress() {
        //given
        String emailTest="thabiso@gmail.com";
        Users userTest1=new Users("Thabiso", "Ngubane","12356",
                "9109347830893", "thabiso@gmail.com", "business man", 1L,
                0, 0);

        //when
        userTest1.setEmailAddress(emailTest);

        //then
        assertTrue(userTest1.getEmailAddress().equals(emailTest));
    }

    @Test
    @Disabled
    void login() {
    }
}