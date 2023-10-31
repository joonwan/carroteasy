package com.carrot.easy.service;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.InterestItem;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test() throws Exception {
        //given
        Member member = new Member("asd","asd","asd",36.5,new Address("asd","asd","asd"));
        Member seller = new Member("seller","seller","seller",36.5,new Address("asd","asd","asd"));
        Item item1 = new Item("a", seller, 10, "this is content", 0, seller.getAddress(), LocalDateTime.now(), null);
        Item item2 = new Item("b", seller, 20, "this is content", 0, seller.getAddress(), LocalDateTime.now(), null);

        memberService.saveMember(member);
        memberService.saveMember(seller);
        itemService.saveItem(item1);
        itemService.saveItem(item2);


        itemService.changeInterestItem(item1.getId(),member.getId());
        itemService.changeInterestItem(item2.getId(),member.getId());

        em.flush();
        em.clear();
        //when

        Item findItem1 = itemService.findItem(item1.getId());
        Item findItem2 = itemService.findItem(item2.getId());

        List<InterestItem> interestItems = memberService.getInterestItems(member.getId());
        ArrayList<Item> items = new ArrayList<>();
        for (InterestItem interestItem : interestItems) {
            items.add(interestItem.getItem());
        }

        //then
        Assertions.assertThat(items).contains(findItem1,findItem2);

    }

}