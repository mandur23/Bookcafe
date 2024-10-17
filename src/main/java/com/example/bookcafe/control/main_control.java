package com.example.bookcafe.control;

import com.example.bookcafe.dto.MemberDTO;
import com.example.bookcafe.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class main_control {
    // 생성자 주입
    private final MemberService memberService;
    // 홈 페이지 요청 처리
    @GetMapping("/")
    public String index(Model model) {
        return "index"; // index.html 반환
    }
    // 로그인 페이지 요청 처리
    @PostMapping("/login")
    public String loginPage(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "redirect:/MainHome";
        }
        return "login_page"; // login_page.html 반환
    }
    @GetMapping("/login")
    public String loginFrom(){
        return "login_page";
    }
    @GetMapping("/MainHome")
    public String MainHome() {
        return "Main_home";
    }
    // 회원 가입 페이지 요청 처리
    @GetMapping("/signup")
    public String signUpPage() {
        return "sign_up_page"; // sign_up_page.html 반환
    }
    // 가입 요청 처리
    @PostMapping("/signup")
    public String signUp(@ModelAttribute MemberDTO memberDTO) {
        System.out.println(memberDTO);
        memberService.idsave(memberDTO);
        return "login_page"; // 홈 페이지로 리다이렉트
    }
    @GetMapping("/logout")
    public String logout() {
        return "Logout";
    }
}
