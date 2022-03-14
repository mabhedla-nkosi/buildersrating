package com.buildersrating.buildersrating.businesstypes;

import javax.persistence.*;

@Entity
@Table(name = "tblBusinessTypes")
public class BusinessTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bTypeId;
    private String businessType;
    private Long businessId;
    private int deleted;
    private int suspended;

    public BusinessTypes() {
    }

    public BusinessTypes(Long bTypeId, String businessType, Long businessId,int deleted, int suspended) {
        this.bTypeId = bTypeId;
        this.businessType = businessType;
        this.businessId = businessId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public BusinessTypes(String businessType, Long businessId,int deleted, int suspended) {
        this.businessType = businessType;
        this.businessId = businessId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Long getbTypeId() {
        return bTypeId;
    }

    public void setbTypeId(Long bTypeId) {
        this.bTypeId = bTypeId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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
        return "BusinessTypes{" +
                "bTypeId=" + bTypeId +
                ", businessType='" + businessType + '\'' +
                ", businessId=" + businessId +
                ", deleted=" + deleted +
                ", suspended=" + suspended +
                '}';
    }
}
