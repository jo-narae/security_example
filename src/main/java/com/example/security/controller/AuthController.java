package com.example.security.controller;

import com.example.security.common.config.JwtTokenProvider;
import com.example.security.domain.Member;
import com.example.security.dto.LoginDTO;
import com.example.security.service.AuthService;
import com.example.security.service.KakaoService;
import com.example.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    KakaoService kakaoService;

    @Autowired
    MemberService memberService;

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/kakao/login")
    public String kakaoLogin(@RequestBody Member req, HttpServletResponse response) {
        Member member = req;

        try {
            member = memberService.findBySocialId(req.getSocialId()).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            Member newMember = new Member(null, req.getSocialId(), null, null, req.getNickname());
            memberService.save(newMember);
            member.setSocialId(newMember.getSocialId());
            member.setNickname(newMember.getNickname());
        }

        String accessToken = jwtTokenProvider.createToken(member.getId(), member.getEmail(), member.getNickname());

        ResponseCookie cookie = ResponseCookie.from("X-AUTH-TOKEN", accessToken)
                .domain("localhost")
                .sameSite("Lax")
                .maxAge(30 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return accessToken;
    }


    @PostMapping("/join")
    public Member saveMember(@RequestBody Member member) {
        return memberService.save(member);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) {
        Member member = authService.validUserId(loginDTO.getEmail());
        authService.validPassword(loginDTO.getPassword(), member.getPassword());

        String accessToken = jwtTokenProvider.createToken(member.getId(), member.getEmail(), member.getNickname());

        ResponseCookie cookie = ResponseCookie.from("X-AUTH-TOKEN", accessToken)
                .domain("localhost")
                .sameSite("Lax")
                .maxAge(30 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return accessToken;
    }
}
