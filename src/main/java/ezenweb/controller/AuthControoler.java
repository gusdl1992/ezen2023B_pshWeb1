package ezenweb.controller;


import ezenweb.Service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/auth")    // 해당 클래스의 매핑 ( 주로 해당 클래스내 함수들의 매핑 주소중에 공통 )
public class AuthControoler {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    // 1. email 인증 요청
    @GetMapping("/email/req")
    public boolean getEmailReq(@RequestParam String email){
        System.out.println("AuthControoler.getEmailReq");
        System.out.println("email = " + email);
        
        // 1. 난수 이용한 6자리 인증 코드 발급
            // 1. 난수 객체 생성
        Random random = new Random();
            // 2. 6번
        String ecode = "";  // 자리수를 맞추려고 앞에 0 이 들어갈수 있다.
        for (int i = 1 ; i <= 6 ; i++){
            // 3. 난수 생성 해서 변수에 누적 문자로 연결하기
            ecode += random.nextInt(10); // 10 : 0~9   // . nextInt( 미만 ) + 시작번호
        }
        System.out.println("ecode = " + ecode); // 이메일 전송 안했을떄 테스트를위해

        // 2. HTTP 세션에 특정 시간만큼 발급된 인증코드 보관
            // 1. 세션에 속성 추가 해서 발급된 인증코드 대입하기
        request.getSession().setAttribute("ecode" , ecode);
            // 2. 세션에 생명주기 넣어주기
        request.getSession().setMaxInactiveInterval( 120 ); // 초단위

        // 3. 발급된 인증코드를 인증할 이메일로 전송
        emailService.send(email , "EZEN WEB 인증코드" , "인증코드 : "+ ecode + " 입니다.");
        
        return true;
    }

    // 2. email 인증 확인
    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String ecodeinput){
        System.out.println("AuthControoler.getEmailCheck");
        System.out.println("ecodeinput = " + ecodeinput);
        
        // 1. HTTP 세션에 보관했던 인증코드를 꺼내서
            // 1. 세션 속성 호출
        Object object = request.getSession().getAttribute("ecode");
            // 2. 만약에 세션에 속성이 존재하면
        if (object != null){
            String ecode =  (String)object; // 강제 타입변환
            // 2. 입력받은 인증코드와 생성된 인증코드와 일치 여부
            if(ecode.equals( ecodeinput ) ){
                return true;
            }
        }
        return false;
    }
}
