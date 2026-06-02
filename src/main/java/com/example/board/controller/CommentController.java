package com.example.board.controller;

import com.example.board.dto.CommentDTO;
import com.example.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public String save(CommentDTO commentDTO, HttpSession session){

        String loginEmail = (String) session.getAttribute("loginEmail");

        commentDTO.setCommentWriter(loginEmail);

        commentService.save(commentDTO);

        return "redirect:/board/" +
                commentDTO.getBoardId();
    }
}
