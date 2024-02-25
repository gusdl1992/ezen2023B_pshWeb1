package 개인연습._20240224회원게시판.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import 개인연습._20240224회원게시판.medel.dao.Dao;
import 개인연습._20240224회원게시판.medel.dto.MemberDto;

@Controller
public class MemberController {
    // 객체 자동 주입
    @Autowired
    Dao dao;

    // 메인 페이지 요청
    @GetMapping("/")
    public String home(){
        System.out.println("MemberController.home");
        return "/개인연습HTML/회원게시물연습0224/HOME.html";
    }

    // 회원가입 페이지 요청
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "/개인연습HTML/회원게시물연습0224/member.html";
    }


    // 로그인 페이지 요청
    @GetMapping("/member/login")
    public String viewlogin(){
        System.out.println("MemberController.viewlogin");
        return "/개인연습HTML/회원게시물연습0224/login.html";
    }

    // 회원가입 요청 처리
    @PostMapping("/member/signUp")
    @ResponseBody
    public boolean signUp(MemberDto memberDto){
        System.out.println("MemberController.signUp");
        System.out.println("signUp -> memberDto = " + memberDto);

        // dao 에 보내 boolean 으로 결과 받기
        boolean result = dao.signUp(memberDto);
        return result;
    }

    // 로그인 요청 처리
    @PostMapping("/member/signIn")
    @ResponseBody
    public boolean signIn(HttpServletRequest request , MemberDto memberDto){
        System.out.println("MemberController.signIn");
        System.out.println("signIn -> memberDto = " + memberDto);

        boolean result = dao.signIn(memberDto);
        // 로그인이 성공 한다면 회원번호 찾기
        if (result){
            int mno = dao.findMno(memberDto);
            // HttpServletRequest request 선언 하여 세션 에 로그인한 회원번호 저장
            request.getSession().setAttribute("user" , mno);

            System.out.println("세션 : mno = " + mno);
        }
        return result;
    }

    // 회원번호 세션에서 가져오기 예
//    public String userMno(HttpServletRequest request , Model model ){
//        HttpSession session = request.getSession();
//        String mno = (String) session.getAttribute("user");
//        System.out.println("userMno = " + mno);
//        return mno;
//    }

    @GetMapping("/board/view")
    public String viewBoard(HttpServletRequest request , Model model){
        System.out.println("MemberController.viewBoard");
        Object qq = request.getSession().getAttribute("user");
        // Object 타입 String 으로 형변환
        String sessionKey = String.valueOf(qq);
        System.out.println("sessionKey = " + sessionKey);
        model.addAttribute("userMno" , sessionKey);
        return "/개인연습HTML/회원게시물연습0224/board.html";
    }





} // c e
