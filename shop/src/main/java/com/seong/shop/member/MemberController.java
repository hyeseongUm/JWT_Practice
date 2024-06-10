package com.seong.shop.member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

   private final MemberService memberService;

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

        return "redirect:/list";
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

        return "mypage.html";
    }
}
