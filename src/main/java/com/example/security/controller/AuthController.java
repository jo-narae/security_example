package com.example.security.controller;

import com.example.security.common.config.JwtTokenProvider;
import com.example.security.domain.Member;
import com.example.security.dto.LoginDTO;
import com.example.security.service.AuthService;
import com.example.security.service.KakaoService;
import com.example.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/kakao/login")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
        System.out.println("#########" + code);
        return kakaoService.getAccessToken(code);
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
