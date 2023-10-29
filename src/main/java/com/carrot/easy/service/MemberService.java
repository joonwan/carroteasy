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
    public Long saveMember(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Member findMemberByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }



}
