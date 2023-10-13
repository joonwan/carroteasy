package com.carrot.easy.repository;

import com.carrot.easy.domain.LifePost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class LifePostRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(LifePost lifePost){
        em.persist(lifePost);
        return lifePost.getId();
    }

    public LifePost findById(Long postId){
       return em.find(LifePost.class, postId);
    }
}
