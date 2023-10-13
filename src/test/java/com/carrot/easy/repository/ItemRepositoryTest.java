package com.carrot.easy.repository;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import com.carrot.easy.repository.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ItemRepositoryTest {

    ItemRepository itemRepository;
    MemberRepository memberRepository;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository, MemberRepository memberRepository) {
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
    }

    @Test
    @Commit
    @DisplayName("item save and find test")
    public void itemSaveAbdFind() throws Exception {
        //given
        Member member = new Member("test Member", 36.5, new Address("pusan", "namgu", "yongdang"));
        memberRepository.save(member);

        Item item = new Item("testItem", member,1000, 0, member.getAddress(), LocalDateTime.now());
        //when
        Long itemId = itemRepository.save(item);

        Item findItem = itemRepository.findById(itemId);
        //then

        Assertions.assertThat(item).isEqualTo(findItem);
    }
}