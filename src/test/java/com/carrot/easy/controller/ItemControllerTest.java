package com.carrot.easy.controller;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import com.carrot.easy.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemControllerTest {

    @Autowired
    private  MemberService memberService;

    @Test
    public void interestTest() throws Exception {
        //given

        Member member = new Member("asd","asd","asd",36.5,new Address("ASd","asd","asd"));
        Item item = new Item();
        //when

        //then
    }

}