package com.seong.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity     //DB table 생성
@Setter
@Getter
public class Item {

    // @Id => 자동으로 unique = true 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //id -> auto_increment 기능
    private Long id;

    // @Column => 컬럼의 제약조건 설정
    // nullable = false : 컬럼이 비어있을 경우 테이블 입력 막기 -> not null 컬럼에 값이 없을 경우 입력 못하게
    // length = 1000 => 강제 길이 변경 / columnDefinition = "TEXT" => TEXT 로 강제 변환
    @Column
    private String title;
    private Integer price;

    // Object 변수를 한 번에 출력하는 법 => method로 만들기 or @toString 어노테이션
    public String toString(){
        return this.title + this.price;
    }
}
