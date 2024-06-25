package com.seong.shop.comment;

import com.seong.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void list(Long parentId, Model model){
       List<Comment> comments = commentRepository.findAllByParentId(parentId);
       model.addAttribute("comments",comments);
    }

    public void addComment(Comment comment, Authentication auth){
        System.out.println(comment);
        CustomUser user = (CustomUser) auth.getPrincipal();
        comment.setUsername(user.getUsername());
        comment.setContent(comment.getContent());
        comment.setParentId(comment.getParentId());
        commentRepository.save(comment);
    }
}
