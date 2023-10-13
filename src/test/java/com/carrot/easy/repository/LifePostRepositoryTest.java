package com.carrot.easy.repository;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.LifePost;
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

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class LifePostRepositoryTest {

    private LifePostRepository postRepository;
    private MemberRepository memberRepository;

    @Autowired
    public LifePostRepositoryTest(LifePostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


    @Test
    @Commit
    @DisplayName("create post and find test")
    public void saveAndFind() throws Exception {
        //given
        Member member = new Member("test Member", 36.5, new Address("pusan", "namgu", "yongdang"));
        Long memberId = memberRepository.save(member);
        //when

        LifePost lifePost = new LifePost("testPost", "asedsadada", member, LocalDateTime.now(), 0);
        Long postId = postRepository.save(lifePost);

        LifePost findPost = postRepository.findById(postId);
        //then

        Assertions.assertThat(lifePost).isEqualTo(findPost);
    }
}