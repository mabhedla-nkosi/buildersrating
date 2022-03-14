package com.buildersrating.buildersrating.ficadocuments;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblFicaDocuments")
public class FicaDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    private Blob identityDocument;
    private Blob proofOfAddress;
    private LocalDateTime expiryDate;
    private Long businessId;
    private Long userId;
    private int deleted;
    private int suspended;

    public FicaDocuments() {
    }

    public FicaDocuments(Long docId, Blob identityDocument, Blob proofOfAddress, LocalDateTime expiryDate, Long businessId, Long userId,
                         int deleted, int suspended) {
        this.docId = docId;
        this.identityDocument = identityDocument;
        this.proofOfAddress = proofOfAddress;
        this.expiryDate = expiryDate;
        this.businessId = businessId;
        this.userId = userId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public FicaDocuments(Blob identityDocument, Blob proofOfAddress, LocalDateTime expiryDate,
                         Long businessId, Long userId, int deleted, int suspended) {
        this.docId = docId;
        this.identityDocument = identityDocument;
        this.proofOfAddress = proofOfAddress;
        this.expiryDate = expiryDate;
        this.businessId = businessId;
        this.userId = userId;
        this.deleted=deleted;
        this.suspended=suspended;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Blob getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(Blob identityDocument) {
        this.identityDocument = identityDocument;
    }

    public Blob getProofOfAddress() {
        return proofOfAddress;
    }

    public void setProofOfAddress(Blob proofOfAddress) {
        this.proofOfAddress = proofOfAddress;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
        return "FicaDocuments{" +
                "docId=" + docId +
                ", identityDocument=" + identityDocument +
                ", proofOfAddress=" + proofOfAddress +
                ", expiryDate=" + expiryDate +
                ", businessId=" + businessId +
                ", userId=" + userId +
                ", deleted=" + deleted +
                ", suspended=" + suspended +
                '}';
    }
}
