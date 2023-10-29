package com.carrot.easy.repository;

import com.carrot.easy.domain.InterestItem;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InterestItemRepository {

    @PersistenceContext
    private EntityManager em;


    public Long save(InterestItem interestItem){
        em.persist(interestItem);
        return interestItem.getId();
    }


    public InterestItem findById(Member member, Item item){
       return (InterestItem) em.createQuery("select ii from InterestItem ii where ii.member = :member and ii.item = :item")
               .setParameter("member", member)
               .setParameter("item", item)
               .getSingleResult();
    }
    public void remove(InterestItem interestItem){
        em.remove(interestItem);
    }


    public List<InterestItem> findAll(Long memberId){
        return em.createQuery("select i from InterestItem i where i.member.id = :memberId")
                .setParameter("memberId",memberId)
                .getResultList();
    }
}
