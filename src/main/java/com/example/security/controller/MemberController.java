package com.example.security.controller;

import com.example.security.domain.Member;
import com.example.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getUser() {
        return memberService.findByAll();
    }

    @GetMapping("/{id}")
    public Optional<Member> getUser(@PathVariable("id") long id) {
        return memberService.findById(id);
    }
}
