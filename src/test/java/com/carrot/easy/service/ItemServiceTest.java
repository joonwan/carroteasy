package com.carrot.easy.service;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.InterestItem;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class ItemServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;

    @Test
    public void addInterestItemTest() throws Exception {
        //given
        Member member = new Member("test1", 36.1, new Address("a", "v", "c"));
        Member member2 = new Member("test2", 36.1, new Address("a", "v", "c"));
        memberService.saveMember(member);
        memberService.saveMember(member2);

        Item item1 = new Item("item1", member, 1000, "asddd", 0, member.getAddress(), LocalDateTime.now(),null);
        Item item2 = new Item("item2", member, 2000, "asdddddddd", 0, member.getAddress(), LocalDateTime.now(),null);
        Item item3 = new Item("item3", member, 2000, "asdddddddd", 0, member.getAddress(), LocalDateTime.now(),null);

        itemService.saveItem(item1);
        itemService.saveItem(item2);
        itemService.saveItem(item3);


        itemService.addInterestItem(member.getId(), item1.getId());
        itemService.addInterestItem(member.getId(), item2.getId());

        itemService.addInterestItem(member2.getId(), item1.getId());
        itemService.addInterestItem(member2.getId(), item3.getId());


        em.flush();
        em.clear();

        Member findMember = memberService.findMember(member.getId());
        Member findMember2 = memberService.findMember(member2.getId());

        List<InterestItem> interestItems = findMember.getInterestItems();
        interestItems.stream().forEach((i)-> System.out.println(i.getItem().getItemName()));

        System.out.println("============");

        List<InterestItem> interestItems2 = findMember2.getInterestItems();
        interestItems2.stream().forEach((i)-> System.out.println(i.getItem().getItemName()));


        //when

        //then
    }
}