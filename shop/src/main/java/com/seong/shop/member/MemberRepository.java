package com.seong.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);
    // findBy컬럼명(파라미터) : 하나만 가지고옴
    // findByAll컬럼명(파라미터) : 해당하는 데이터 모두 가져옴 : List

    /*
     Derived query methods
     - and, or 조건
     - 특정문자 포함되어있는지 검색
     - 특정 숫자 이상/이하 인거 검색
     - 정렬
     */
}
