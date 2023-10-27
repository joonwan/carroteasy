package com.carrot.easy.repository;

import com.carrot.easy.domain.InterestItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InterestItemRepository {

    @PersistenceContext
    private EntityManager em;


    public Long save(InterestItem interestItem){
        em.persist(interestItem);
        return interestItem.getId();
    }


    public List<InterestItem> findAll(Long memberId){
        return em.createQuery("select i from InterestItem i where i.member.id = :memberId")
                .setParameter("memberId",memberId)
                .getResultList();
    }
}
