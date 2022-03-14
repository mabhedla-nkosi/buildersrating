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
    private String password;
    private String idNumber;
    private String emailAddress;
    private String userStatus;
    private Long groupId;
    private LocalDateTime registeredDate;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;
    private int suspended;
    private int deleted;

    public Users() {
    }

    public Users(Long userId, String firstName, String lastName,String password,
                 String idNumber, String emailAddress, String userStatus, Long groupId,
                 LocalDateTime registeredDate, LocalDateTime loginDate, LocalDateTime logoutDate,
                 int suspended, int deleted) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password=password;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.userStatus = userStatus;
        this.groupId = groupId;
        this.registeredDate=registeredDate;
        this.loginDate = loginDate;
        this.logoutDate = logoutDate;
        this.suspended=suspended;
        this.deleted=deleted;
    }

    public Users(String firstName, String lastName,String password,
                 String idNumber, String emailAddress, String userStatus, Long groupId,
                 int suspended, int deleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password=password;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.userStatus = userStatus;
        this.groupId = groupId;
        this.suspended=suspended;
        this.deleted=deleted;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
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

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @PrePersist
    public void onCreate() {
        this.registeredDate =  LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", groupId=" + groupId +
                ", registeredDate=" + registeredDate +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                ", suspended=" + suspended +
                ", deleted=" + deleted +
                '}';
    }
}
