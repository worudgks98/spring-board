package com.example.board.controller;

import com.example.board.dto.CommentDTO;
import com.example.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         HttpSession session){

        CommentDTO commentDTO =
                commentService.findById(id);

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null ||
                !loginEmail.equals(
                        commentDTO.getCommentWriter())){

            return "redirect:/board/" +
                    commentDTO.getBoardId();
        }

        Long boardId = commentDTO.getBoardId();

        commentService.delete(id);

        return "redirect:/board/" + boardId;
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id,
                             Model model,
                             HttpSession session){

        CommentDTO commentDTO =
                commentService.findById(id);

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null ||
                !loginEmail.equals(
                        commentDTO.getCommentWriter())){

            return "redirect:/board/" +
                    commentDTO.getBoardId();
        }

        model.addAttribute("comment", commentDTO);

        return "commentUpdate";
    }

    @PostMapping("/update")
    public String update(CommentDTO commentDTO){

        commentService.update(commentDTO);

        return "redirect:/board/" +
                commentDTO.getBoardId();
    }
}
