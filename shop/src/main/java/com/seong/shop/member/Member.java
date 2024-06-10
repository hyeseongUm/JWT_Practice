package com.seong.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

}
