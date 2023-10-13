package com.carrot.easy.service;

import com.carrot.easy.domain.Member;
import com.carrot.easy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long join(Member member){
        return memberRepository.save(member);
    }

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
