package com.seong.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(MemberDTO memberDTO) throws Exception {
        Member member = new Member();
        if(memberDTO.getUsername().length()<6 || memberDTO.getPassword().length() <8){
            throw new Exception("아이디 또는 패스워드의 길이가 너무 짧습니다");
        }
        Optional<Member> isUser = memberRepository.findByUsername(memberDTO.getUsername());
        if(isUser.isPresent()){
            throw new Exception("이미 존재하는 아이디입니다");
        } else {
            member.setUsername(memberDTO.getUsername());
            member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            member.setName(memberDTO.getName());
            memberRepository.save(member);
        }
    }
}
