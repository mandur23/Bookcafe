package com.example.bookcafe.control;

import com.example.bookcafe.dto.MemberDTO;
import com.example.bookcafe.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final MemberService memberService;
    // 홈 페이지 요청 처리
    @GetMapping("/")
    public String index(Model model) {
        return "index"; // index.html 반환
    }
    // 로그인 페이지 요청 처리
    @PostMapping("/login")
    public String loginPage(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "redirect:/MainHome";
        }
        model.addAttribute("loginError", "이메일 또는 비밀번호가 잘못되었습니다."); // 로그인 실패 메시지 추가
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
    public String signupForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "sign_up_page";
    }
    // 가입 요청 처리
    @PostMapping("/signup")
    public String registerMember(@ModelAttribute MemberDTO memberDTO, Model model) {
        String registrationResult = memberService.registerMember(memberDTO);

        if ("회원가입이 성공적으로 완료되었습니다.".equals(registrationResult)) {
            return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로 이동
        } else {
            model.addAttribute("error", registrationResult); // 오류 메시지 전달
            return "sign_up_page"; // 오류 발생 시 회원가입 페이지로 다시 이동
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "Logout"; // 로그아웃 페이지 반환
    }
    @GetMapping("/MemberFix")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        if (myEmail == null) {
            model.addAttribute("error", "로그인이 필요합니다."); // 세션에 이메일이 없으면 에러 메시지 추가
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        MemberDTO member = memberService.updateForm(myEmail);
        if (member != null) {
            model.addAttribute("member", member); // member 객체를 모델에 추가
        } 
        else {
            model.addAttribute("error", "회원 정보를 찾을 수 없습니다."); // 오류 메시지 추가
        }
        return "MemberFix"; // MemberFix.html 반환
    }
    @PostMapping("/MemberFix")
    public String updateMember(@ModelAttribute MemberDTO memberDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        String myEmail = (String) session.getAttribute("loginEmail");
        if (myEmail == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다."); // 세션에 이메일이 없으면 에러 메시지 추가
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // memberDTO에 세션에서 가져온 이메일 설정
        memberDTO.setMemberEmail(myEmail);
        
        String message = memberService.MemberFix(memberDTO); // 수정 메시지 받기
        redirectAttributes.addFlashAttribute("success", message); // 성공 메시지를 리다이렉트 속성에 추가
        return "redirect:/MainHome"; // 수정 후 홈 페이지로 리다이렉트
    }
    @GetMapping("/deleteMember")
    public String deleteMember(HttpSession session, RedirectAttributes redirectAttributes) {
        String myEmail = (String) session.getAttribute("loginEmail");
        if (myEmail == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다."); // 세션에 이메일이 없으면 에러 메시지 추가
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        String message = memberService.deleteMember(myEmail); // 탈퇴 메시지 받기
        session.invalidate(); // 세션 무효화
        redirectAttributes.addFlashAttribute("success", message); // 성공 메시지를 리다이렉트 속성에 추가
        return "redirect:/"; // 홈 페이지로 리다이렉트
    }
}
