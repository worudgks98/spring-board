package com.example.board.controller;

import com.example.board.dto.MemberDTO;
import com.example.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "memberSave";
    }

    @PostMapping("/save")
    public String save(MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDTO memberDTO,
                        HttpSession session){

        boolean result = memberService.login(memberDTO);

        if(result){
            session.setAttribute(
                    "loginEmail",
                    memberDTO.getMemberEmail());
            System.out.println("로그인 성공");

            return "redirect:/board/";
        }
        System.out.println("로그인 실패");

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect:/";
    }
}