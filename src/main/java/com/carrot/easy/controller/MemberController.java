package com.carrot.easy.controller;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Member;
import com.carrot.easy.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:19006")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public MyInfoDto getMyInfo(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        MyInfoDto myInfoDto = new MyInfoDto();
        myInfoDto.setMemberName(member.getName());
        return myInfoDto;
    }

    @PostMapping("/new")
    public void saveMember(@RequestBody MemberDto memberDto) {

        log.info("hello member register controller");
        log.info("{}", memberDto.toString());
        Member member = new Member(memberDto.getLoginId(), memberDto.getPassword(), memberDto.getName(), 36.5, new Address(memberDto.getCity(), memberDto.getGoo(), memberDto.getDong()));
        log.info("{}", member.toString());
        memberService.saveMember(member);
    }

    @Data
    static class MemberDto {

        private String loginId;
        private String password;
        private String name;
        private String city;
        private String goo;
        private String dong;

    }

    @Data
    static class MyInfoDto {
        private String memberName;
    }

}
