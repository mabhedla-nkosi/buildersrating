package com.buildersrating.buildersrating.business;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblBusiness")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessId;
    private String businessName;
    private int bType;
    private String bRegNumber;
    private String bAddress;
    private String approvalStatus;
    private LocalDateTime decisionDate;
    private int userId;
    private int deleted;
    private int suspended;

    public Business() {
    }

    public Business(Long businessId, String businessName, int bType, String bRegNumber, String bAddress,
                    String approvalStatus, LocalDateTime decisionDate, int userId, int deleted, int suspended) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.bType = bType;
        this.bRegNumber = bRegNumber;
        this.bAddress = bAddress;
        this.approvalStatus = approvalStatus;
        this.decisionDate = decisionDate;
        this.userId = userId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Business(String businessName, int bType, String bRegNumber, String bAddress,
                    String approvalStatus, LocalDateTime decisionDate, int userId, int deleted, int suspended) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.bType = bType;
        this.bRegNumber = bRegNumber;
        this.bAddress = bAddress;
        this.approvalStatus = approvalStatus;
        this.decisionDate = decisionDate;
        this.userId = userId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getbType() {
        return bType;
    }

    public void setbType(int bType) {
        this.bType = bType;
    }

    public String getbRegNumber() {
        return bRegNumber;
    }

    public void setbRegNumber(String bRegNumber) {
        this.bRegNumber = bRegNumber;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LocalDateTime getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDateTime decisionDate) {
        this.decisionDate = decisionDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", bType=" + bType +
                ", bRegNumber='" + bRegNumber + '\'' +
                ", bAddress='" + bAddress + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", decisionDate=" + decisionDate +
                ", userId=" + userId +
                ", deleted=" + deleted +
                ", suspended=" + suspended +
                '}';
    }
}
