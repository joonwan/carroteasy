package com.carrot.easy;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        @PersistenceContext
        private EntityManager em;
        public void dbInit(){

            Member member1 = new Member("testUser1",36.5,new Address("pusan","haoondae","dingdong"));
            Member member2 = new Member("testUser2",36.5,new Address("seoul","gangnam","dingdong"));
            em.persist(member1);
            em.persist(member2);



//            Item item1 = new Item("item1",member1,1000,"this is content 1",0,member1.getAddress(),LocalDateTime.now(),null);
//            Item item2 = new Item("item2", member2, 2000, "this is content 2",0, member2.getAddress(), LocalDateTime.now(),null);
//
//            em.persist(item1);
//            em.persist(item2);

        }
    }
}
