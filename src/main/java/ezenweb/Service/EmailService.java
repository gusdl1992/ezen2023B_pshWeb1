package ezenweb.Service;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

// SMTP : 간이 우편 전송 프로토콜 ( 메일 전송 )
    // - 자바에서 메일보내기 ( 네이버 계정 )
    // 1. SMTP 라이브러리 필요.https://start.spring.io/
    // 2. Dependencies : Java Mail Sender
    // implementation 'org.springframework.boot:spring-boot-starter-mail'

    // 3. 이메일 전송
    // 1. 이메일 내용을 구성 => 구성 포멧 : MIME( SMTP 사용할떄 사용되는 포멧 )

    // 4. 보내는사람 이메일 인증
    // application.properties

    /*


        자바                  네이버             받는사람도메인(회사 kakao)
                --smtp--->
       kgs2072@naver.com       인증
                                      ---smtp-->
                                                itdanja@kakao.com
                            보내는사람 이메일
                            보내는사람 비밀번호

                            git commit [ X ]

    */


    // 1. java(Spring) 지원하는 smtp 객체 필요 = javaMailSender
    @Autowired
    private JavaMailSender javaMailSender;  // javamail 제공하는 객체 라이브러리

    public void send(String toEmail , String subject , String content){
        // * 메일 내용물들을 포멧하기 위한 MIME 형식 객체
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // 1. 메일 구성
            // MimeMessageHelper( mime객체 , 첨부파일여부 , 인코딩타입 ) : 내용물 구성
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message , true , "utf-8");
            // 2. 메일 보내는 사람
            mimeMessageHelper.setFrom("tlgusdl92@naver.com"); // 관리자 이메일
            // 3. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail); // 클라이언트(회원) 이메일
            // 4. 메일 제목
            mimeMessageHelper.setSubject(subject); // ( 매개변수 )
            // 5. 메일 내용
            mimeMessageHelper.setText(content); // ( 매개변수 )
            // 6. 메일 전송
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("이메일 실패여부 : e = " + e);
        }
    }// send 메서드 종료
} // c e
