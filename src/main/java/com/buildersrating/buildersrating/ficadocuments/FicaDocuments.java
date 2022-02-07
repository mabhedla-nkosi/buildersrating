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

    public FicaDocuments() {
    }

    public FicaDocuments(Long docId, Blob identityDocument, Blob proofOfAddress, LocalDateTime expiryDate, Long businessId, Long userId) {
        this.docId = docId;
        this.identityDocument = identityDocument;
        this.proofOfAddress = proofOfAddress;
        this.expiryDate = expiryDate;
        this.businessId = businessId;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "FicaDocuments{" +
                "docId=" + docId +
                ", identityDocument=" + identityDocument +
                ", proofOfAddress=" + proofOfAddress +
                ", expiryDate=" + expiryDate +
                ", businessId=" + businessId +
                ", userId=" + userId +
                '}';
    }
}
