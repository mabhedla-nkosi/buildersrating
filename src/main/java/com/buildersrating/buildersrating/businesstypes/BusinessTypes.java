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

    public BusinessTypes() {
    }

    public BusinessTypes(Long bTypeId, String businessType, Long businessId) {
        this.bTypeId = bTypeId;
        this.businessType = businessType;
        this.businessId = businessId;
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

    @Override
    public String toString() {
        return "BusinessTypes{" +
                "bTypeId=" + bTypeId +
                ", businessType='" + businessType + '\'' +
                ", businessId=" + businessId +
                '}';
    }

}
