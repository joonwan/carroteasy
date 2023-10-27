package com.carrot.easy.repository;

import com.carrot.easy.controller.UploadFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UploadFileRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(UploadFile uploadFile){
        em.persist(uploadFile);
        return uploadFile.getId();
    }

    public UploadFile findById(Long fileId){
        return em.find(UploadFile.class, fileId);
    }
}
