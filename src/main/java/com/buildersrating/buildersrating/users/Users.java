package com.buildersrating.buildersrating.users;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblUsers")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String emailAddress;
    private String userStatus;
    private Long groupId;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;

    public Users() {
    }

    public Users(Long userId, String firstName, String lastName, String idNumber, String emailAddress, String userStatus, Long groupId, LocalDateTime loginDate, LocalDateTime logoutDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.userStatus = userStatus;
        this.groupId = groupId;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", groupId=" + groupId +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                '}';
    }
}
