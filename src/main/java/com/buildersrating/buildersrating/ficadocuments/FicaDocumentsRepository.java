package com.buildersrating.buildersrating.ficadocuments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicaDocumentsRepository extends JpaRepository<FicaDocuments,Long> {
}
