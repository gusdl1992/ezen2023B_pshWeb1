package 개인연습.controller;


import ezenweb.medel.dto.LoginDto;
import ezenweb.medel.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import 개인연습.model.dao.Dao;


// 1단계. V<---->C 사이의 HTTP 통신 방식 설계
// 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
// 3단계. Controller request 매개변수 매핑
// -------------- Dto , Service ---------------//
// 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON

@Controller
public class MemberController {
    // DAO 주입
    @Autowired
    Dao dao;
    int num = 0;
    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        /* params  {   id ="아이디" , pw ="비밀번호" , name="이름" ,   email="이메일" , phone="전화번호" , img ="프로필사진"   }  */
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // --
        boolean result = dao.doPostSignup(memberDto);//Dao처리;
        return result; // Dao 요청후 응답 결과를 보내기.
    }
    // 2. =========== 로그인 처리 요청 ===============
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        /* params    { id ="아이디" , pw ="비밀번호"  }   */
        System.out.println("MemberController.login");
        System.out.println("loginDto = " + loginDto);
        // --
        boolean result = dao.doPostLogin(loginDto); // Dao처리
        if (result){
            // 로그인 true 일시
            num = dao.doFind(loginDto);
            System.out.println("회원번호 = " + num);
            return result; // Dao 요청후 응답 결과를 보내기
        }
        return false; // Dao 요청후 응답 결과를 보내기
    }
    // 3. =========== 회원가입 페이지 요청 ===============
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "개인연습/signup";
    }
    // 4. =========== 로그인 페이지 요청 ===============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "개인연습/login";
    }


    // 5. ======================== 수정 페이지 요청 요청 ========================
    @GetMapping("개인연습/updatepage")
    public String viewUpdate(@PathVariable int no , Model model){
        System.out.println("MemberController.viewUpdate");
        System.out.println("no = " + no);
        // 1. 로그인 시 저장되는 no 가져오기
        MemberDto memberDto = new MemberDto();
        memberDto.setNo(no);
        // 2. 응답결과를 뷰 템플릿 에게 보낼 준비 model.
        model.addAttribute("article" , memberDto);
        // 3. 뷰 페이지 설정
        return "/member/update";
    }

    // 6. ======================== 수정  요청 ========================
    @PostMapping("/member/update")
    public String update(MemberDto memberDto){
        System.out.println("MemberController.update");
        System.out.println("memberDto = " + memberDto);
        //
        //
        // 3. 뷰 페이지 설정
        return "개인연습/index";
    }

    // 7. ======================== 삭제  요청 ========================
    
    
}

/*

    요청 HTTP		        								                                                                     서버 HTTP Requst	                             서버HTTP Response
기능		            URL	                    params 설명		            method	        contentType			                    mapping		        parms		                응답			            contentType
1.회원가입 처리요청       /member/signup          {                           POST          application/x-www-form-urlencode        @PostMapping          MemberDto dto         boolean : true/false                @ResponseBody     application/json;
                                                 id ="아이디",
                                                 pw ="비밀번호",
                                                 name = "이름",
                                                 email = "이메일",
                                                 phone = "전화번호",
                                                 ing = "프로필사진"
                                                }

2.로그인 처리요청      /member/login              {                          POST          application/x-www-form-urlencode        @PostMapping            LoginDto dto         boolean : true/false               @ResponseBody     application/json;
                                                 id ="아이디",
                                                 pw ="비밀번호",
                                                   }


*/
