package item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class ItemController {

    // @RequiredArgsConstructor => Lombok 없이 사용 => Constructor 만들기
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/list")
    String list(Model model){

        List<Item> data = itemRepository.findAll(); // 모든 데이터 가져오기
        System.out.println(data);

        //itemRepository.save(??); // 테이블에 데이터 넣기

        model.addAttribute("name","홍길동");
        return "list.html";
    }

    @GetMapping("/test")
    String hashing(){
        String result = new BCryptPasswordEncoder().encode("123456");
        System.out.println(result);
        // 비번 : Hashing
        // 같은 문자 해싱하면 항상 같은 결과
        // 원래문자 추론 불가능
        return "redirect:/list";
    }
}
