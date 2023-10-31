package com.carrot.easy.repository;

import com.carrot.easy.domain.InterestItem;
import com.carrot.easy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long memberId){
        return em.find(Member.class, memberId);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Member findByLoginId(String loginId) {
        return (Member)em.createQuery("select m from Member m where m.loginId = :loginId")
                .setParameter("loginId", loginId)
                .getSingleResult();
    }


    public List<InterestItem> getInterestItems(Long memberId) {
        Member member = findById(memberId);
        return em.createQuery("select i from Member m join m.interestItems as i where m = :member", InterestItem.class)
                .setParameter("member",member)
                .getResultList();
    }
}
