package com.seong.shop.item;

import com.seong.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    // @RequiredArgsConstructor => Lombok 없이 사용 => Constructor 만들기
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentService commentService;

    // 만들어진 Constructor
   /* @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }*/

    @GetMapping("/list")
    String list(Model model){
        itemService.itemList(model);
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/write")
    String writePost(String title, Integer price){ //@ModelAttribute Item item : input 에 넣은 parameter 값 매칭시켜서 가져옴
        itemService.addItem(title,price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String itemDetail(@PathVariable Long id, Model model){
        itemService.itemDetail(id,model);
        commentService.list(id, model);

        return "detail.html";
    }

    @GetMapping("/list/page/{no}")
    String pagenation(Model model, @PathVariable Integer no){
        itemService.pagination(model, no);

        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename){
        System.out.println("파일명 : "+filename);
        String url = s3Service.createPresignedUrl("test/" + filename);
        System.out.println("presigned-url : "+url);

        return url;
    }

    /* Rest API 사용시 error : try~catch or ExceptionHandler
    // ExceptionHandler : 같은 파일의 모든 API 의 에러 캐치 -> 안의 코드 실행
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");
    }
    */

   /* @GetMapping("/test")
    String hashing(){
        String result = new BCryptPasswordEncoder().encode("123456");
        System.out.println(result);
        // 비번 : Hashing
        // 같은 문자 해싱하면 항상 같은 결과
        // 원래문자 추론 불가능
        return "redirect:/list";
    }*/

}
