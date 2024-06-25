package com.seong.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()){
            throw new UsernameNotFoundException("없는 아이디 입니다");
        }
        Member data =  member.get();
        List<GrantedAuthority> auth = new ArrayList<>();
        if (data.getUsername().equals("nks0561")){
            auth.add(new SimpleGrantedAuthority("ROLE_Admin"));
        }else {
            auth.add(new SimpleGrantedAuthority("ROLE_User"));
        }

        // return 값이 Authentication 에 담김
        return new CustomUser(data.getUsername(),data.getPassword(),auth, data.getName());
    }
}
