package com.seong.shop.comment;

import com.seong.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    String addComment(@ModelAttribute Comment comment, Authentication auth){
        commentService.addComment(comment, auth);
        System.out.println(comment.getParentId());
        return "redirect:/detail/"+comment.getParentId();
    }
}
