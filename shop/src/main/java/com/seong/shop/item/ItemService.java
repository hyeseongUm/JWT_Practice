package com.seong.shop.item;

import lombok.RequiredArgsConstructor;
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
        System.out.println(data);
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

    public void error(){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
