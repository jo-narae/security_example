package com.example.security.controller;

import com.example.security.domain.Member;
import com.example.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getMember() {
        return memberService.findByAll();
    }

    @GetMapping("/{id}")
    public Optional<Member> getMember(@PathVariable("id") long id) {
        return memberService.findById(id);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        return memberService.save(member);
    }
}
