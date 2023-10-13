package com.carrot.easy.repository;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @Commit
    @DisplayName("member entity save and find test")
    public void saveAndFind() throws Exception {
        //given

        Member member = new Member("test Member", 36.5, new Address("pusan", "namgu", "yongdang"));
        Long memberId = memberRepository.save(member);

        //when

        Member findMember = memberRepository.findById(memberId);

        //then

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}