package ezenweb.controller;


import book.dto.ArticleForm;
import ezenweb.medel.dao.Dao;
import ezenweb.medel.dao.MemberDao;
import ezenweb.medel.dto.LoginDto;
import ezenweb.medel.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;




// 1단계. V<---->C 사이의 HTTP 통신 방식 설계
// 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
// 3단계. Controller request 매개변수 매핑
// -------------- Dto , Service ---------------//
// 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON

@Controller
public class MemberController {
    @Autowired
    MemberDao memberDao;

    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        /* params  {   id ="아이디" , pw ="비밀번호" , name="이름" ,   email="이메일" , phone="전화번호" , img ="프로필사진"   }  */
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // --
        boolean result = memberDao.doPostSignup(memberDto); //Dao처리;
        return result; // Dao 요청후 응답 결과를 보내기.
    }

    // * Http 요청 객체 , 매개변수로도 가능
    @Autowired
    private HttpServletRequest request;

    // 2. =========== 로그인 처리 요청 / 세션 저장 ===============
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        /* params    { id ="아이디" , pw ="비밀번호"  }   */
        System.out.println("MemberController.login");
        System.out.println("loginDto = " + loginDto);
        // --
        boolean result = memberDao.doPostLogin(loginDto); // Dao처리
        
        // *로그인 성공시 ( 세션 )
            // 세션 저장소 : 톰캣서버에 *브라우저 마다의 메모리 할당
            // 세션 객체 타입 : Object ( 여러가지 타입들을 저장 하려고 )
            // 1. Http 세션 객체 호출          HttpServletRequest
            // 2. Http 세션 호출              .getSession();
            // 3. Http 세션 데이터 저장        .setAttribute("세션명" , 데이터); -- 자동 형변환 ( 자식 --> 부모)
            // -  Http 세션 데이터 호출        .getAttribute("세션속성명")  -- 강제 형변환 필요 ( 부모 --> 자식 )
            // -  Http 세션 초기화            .invalidate() : 현재 요청 보낸 브라우저의 모든 세션 초기화
            // -  Http 세션 시간 서렂ㅇ        .setMaxInactiveInterval(); -- 초 단위 설정  

        if (result){ // 만약에 로그인 성공이면 세션 부여
            request.getSession().setAttribute("loginDto" , loginDto.getId());  // loginDto : 3
        }
        return result; // Dao 요청후 응답 결과를 보내기
    }

    // 2-2. =========== 로그인 여부 확인 요청 / 세션 호출 ===============
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // * 로그인 여부 확인 : 세션이 있다 없다 확인이랑 같다!
            // 1 -> Http 요청 객체 호출 , 2-> http 세션 객체 호출 3-> http 세션 데이터 호출
            // null 은 형변환이 불가능 하기 때문에 유효성 검사.
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj != null){
            loginDto = (String)(sessionObj);
        }
        return loginDto;
    }

    // 2-3 =========== 로그인 로그아웃 / 세션 초기화 ===============
    @GetMapping("/member/logout")
    @ResponseBody   // 응답받을 대상이 JS ajax
    public boolean doGetLoginOut(){

        // 1. 로그인 관련 세션 초기화
            // 1. 모든 세션 초기화 ( 모든 세션의 속성이 초기화 -> 로그인 세션 외 다른 세션도 고려  )
        request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화
            // 2. 특정 세션 속성 초기화 => 동일한 세션 속성명에 null 대입한다.
        // request.getSession().setAttribute("loginDto" , null);
        return true;
        // 로그아웃 성공시 => 메인페이지 또는 로그인페이지 이동
    }


    // 3. =========== 회원가입 페이지 요청 ===============
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }
    // 4. =========== 로그인 페이지 요청 ===============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }


    // 5. ======================== 수정 페이지 요청 요청 ========================
    @GetMapping("ezenweb/updatepage")
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
        return "ezenweb/index";
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
