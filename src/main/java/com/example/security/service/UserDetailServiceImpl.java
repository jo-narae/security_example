package com.example.security.service;

import com.example.security.domain.Member;
import com.example.security.domain.MemberDetail;
import com.example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(userId).get();
        if(member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        MemberDetail userDetails = new MemberDetail(member.getEmail(), "", authorities);

        return userDetails;
    }
}