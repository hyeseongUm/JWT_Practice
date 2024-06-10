package com.seong.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findPageBy(Pageable page); // pagination -> x 개만 가져옴
    /*
    Slice<Item> findPageBy(Pageable page);
    => page 와 같음 -> console 에 찍히는 것 줄어듦, total 페이지 알려주지 않음
    */
}