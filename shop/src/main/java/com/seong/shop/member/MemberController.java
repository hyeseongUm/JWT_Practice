package com.seong.shop.member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

   private final MemberService memberService;
   private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String join(Authentication auth){
       if(auth.isAuthenticated()){
            return "redirect:/list";
        }
        return "join.html";
    }

    @PostMapping("/join")
    public String addMember(@ModelAttribute MemberDTO memberDTO) throws Exception {
        System.out.println(memberDTO.getUsername());
        memberService.join(memberDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){ // Authentication : 회원정보 가지고 있음
        System.out.println(auth);
        System.out.println(auth.getAuthorities()); // 권한
        System.out.println(auth.getName()); // id
        System.out.println(auth.isAuthenticated()); // 권한여부
        System.out.println(auth.getPrincipal());

        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberData getUser(){
        Optional<Member> member = memberRepository.findById(1L);
        Member result = member.get();
        MemberData data = new MemberData(result.getUsername(),result.getName());

        return data;
    }

    // db 값을 변환해서 전송할 때 -> 필요한 데이터만 전송가능
    /* 장점
       - 데이터의 타입 체크 용이
       - 재사용 쉬움
     */
    class MemberData{
        public String username;
        public String name;

        MemberData(String username, String name){
            this.username = username;
            this.name = name;
        }

    }

}
