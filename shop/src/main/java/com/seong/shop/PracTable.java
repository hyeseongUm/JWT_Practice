package com.seong.shop;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PracTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;
    Date date;
}
