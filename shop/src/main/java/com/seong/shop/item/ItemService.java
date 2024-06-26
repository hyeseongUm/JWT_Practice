package com.seong.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Service : 이것저것 검사 / db 입출력 등등 => 비즈니스 로직
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void itemList(Model model){
        List<Item> data = itemRepository.findAll(); // 모든 데이터 가져오기
        model.addAttribute("items",data);
    }

    public void addItem(String title, Integer price) {
        System.out.println(title+":"+price);
        if(!title.isEmpty() && price!=null) {
            Item item = new Item();
            item.setTitle(title);
            item.setPrice(price);
            itemRepository.save(item);  // 테이블에 데이터 넣기
        }else {
            error();
        }
    }

    public void itemDetail(Long id,Model model){
        Optional<Item> data = itemRepository.findById(id);
        // Optional == null 일 경우 error => 있는지 확인하고 데이터 가져오기
        if(data.isPresent()) {
            System.out.println(data.get());
            model.addAttribute("items", data.get());
        }else {
            error();
        }
    }

    public void pagination(Model model, Integer no){
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(no-1,5));
        // of(몇번째 페이지(0~), 몇개 가져올 건지)
        // List 와 비슷하기 때문에 html 에서 반복문
        // result.getTotalPages(); -> 총 페이지 수 알려줌

        model.addAttribute("items", result);
    }

    public void search(String searchText, Model model){
        // itemRepository.findAllByTitle(searchText);
        // => title 컬럼에서 searchText 와 완전히 일치한 행 모두 가져오기

        // title 컬럼에서 searchText 를 포함하고 있는 모든 행 가져오기 (All 생략가능) -> index 존재 여부 상관없이 반영안돼서 느림
       // List<Item> search =  itemRepository.findAllByTitleContains(searchText);

        List<Item> search = itemRepository.searchTitle(searchText);
        model.addAttribute("searchData",search);

    }

    public void error(){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
