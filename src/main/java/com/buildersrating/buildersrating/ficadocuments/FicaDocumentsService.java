package com.buildersrating.buildersrating.ficadocuments;

import com.buildersrating.buildersrating.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FicaDocumentsService {

    @Autowired
    FicaDocumentsRepository ficaDocumentsRepository;

    public List<FicaDocuments> getFicaDocuments() {
        try{
            return ficaDocumentsRepository.findAll();
        }catch (Exception ex){
            throw new ApiRequestException(ex.getMessage());
        }
    }

    public void addNewDocuments(FicaDocuments ficaDocuments) {
    }

    public void deleteFicaDocuments(Long docId) {
    }

    public void updateFicaDocuments(Long docId, int ratingNumber, String identityDocument, Long proofOfAddress, LocalDateTime expiryDate, Long businessId, LocalDateTime userId) {
    }
}
