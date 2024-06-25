package com.seong.shop.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    public final String name;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String name) {
        super(username, password, authorities);
        this.name = name;
    }
}
