package com.example.board.controller;

import com.example.board.dto.CommentDTO;
import com.example.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm(HttpSession session) {

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null){
            return "login";
        }

        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO,
                       HttpSession session){

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null){
            return "login";
        }

        boardDTO.setBoardWriter(loginEmail);

        boardService.save(boardDTO);

        return "redirect:/board/";
    }

    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){

        boardService.updateHits(id);

        BoardDTO boardDTO = boardService.findById(id);

        List<CommentDTO> commentDTOList =
                commentService.findAll(id);

        model.addAttribute("board", boardDTO);
        model.addAttribute("commentList", commentDTOList);

        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         HttpSession session){

        BoardDTO boardDTO = boardService.findById(id);

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null ||
                !loginEmail.equals(boardDTO.getBoardWriter())){
            return "redirect:/board/";
        }

        boardService.delete(id);

        return "redirect:/board/";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id,
                             Model model,
                             HttpSession session){

        BoardDTO boardDTO = boardService.findById(id);

        String loginEmail =
                (String) session.getAttribute("loginEmail");

        if(loginEmail == null ||
                !loginEmail.equals(boardDTO.getBoardWriter())){
            return "redirect:/board/";
        }

        model.addAttribute("board", boardDTO);

        return "update";
    }

    @PostMapping("/update")
    public String update(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        try {
            boardService.update(boardDTO);
            return "redirect:/board/" + boardDTO.getId();
        } catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/board/update/" + boardDTO.getId();
        }
    }

}
