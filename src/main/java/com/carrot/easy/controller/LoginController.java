package com.carrot.easy.controller;

import com.carrot.easy.domain.Member;
import com.carrot.easy.service.MemberService;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:19006")
public class LoginController {

    private final MemberService memberService;

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {

        String loginId = requestDto.getLoginId();
        String password = requestDto.getPassword();

        Member findMember = memberService.findMemberByLoginId(loginId);

        if (findMember.getPassword().equals(password)) {
            return new LoginResponseDto(findMember.getId(), findMember.getName(), null);
        } else {
            return new LoginResponseDto(null, null, "아이디 또는 비밀번호가 틀립니다.");
        }


    }

    @Data
    static class LoginRequestDto {

        @NotEmpty
        private String loginId;

        @NotEmpty
        private String password;
    }

    @Data
    static class LoginResponseDto {
        private Long memberId;
        private String memberName;
        private String error;

        public LoginResponseDto(Long memberId, String memberName, String error) {
            this.memberId = memberId;
            this.memberName = memberName;
            this.error = error;
        }
    }

}
