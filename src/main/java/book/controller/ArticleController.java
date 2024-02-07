package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 스프링 컨테이너(메모리 저장소) 에 빈(객체) 등록 
// 스프링이 해당 클래스를 사용할수 있다.
// 3. 모든 클라이언트 요청은 컨트롤러로 들어온다

@Slf4j  // 자바에서 간단한 로그 처리
public class ArticleController {
    @Autowired  // 스프링 컨테이너에 등록된 빈 주입한다.
    ArticleDao articleDao;

    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new") // HTTP 요청 경로 : GET 방식 : localhost:80/article/new
    public String newArticleForm(){
        return "articles/new";   // .확장자 빼고 경로는 resources/templates 부터 경로 작성
    }

    // 2. 글쓰기 처리
    // 1.   <form action="/articles/create" method="post">
    // 2. 입력태그 속성의 name 과 DTO 필드의 필드명 일치하면 자동 연결/대입 된다.
    // 3. public 생성자 필요
    @PostMapping("/articles/create")    // HTTP 요청 경로 : POST 방식 localhost:80/articles/create
    public boolean createArticle(ArticleForm form){
        // soutm : 메소드명 출력
        System.out.println("ArticleController.createArticle");
        // soutv : 메소드 매개변수 출력
        //System.out.println("form = " + form);
        
        // ( 디버그 ) 로그 테스트용
        log.debug(form.toString());
        
        // (경고) 로그
        log.warn(form.toString());
        
        // (에러) 로그
        log.error(form.toString());
        
        // ( 정보 ) 로그 배포용
        log.info(form.toString()); // 자동완성 : 메뉴 -> 파일 -> 플러그인 - > 롬복(Lombok) 플러그인 설치
        boolean result = articleDao.crateArticle(form);


        return result;
    }

}

/*
    자유도 떨어짐.

    @어노테이션
        1. 표준 어노테이션 : 자바에서 기본적으로 제공
            @Override   : 메소드 재정의
            등등
        2. 메타 어노테이션 : p.64 하단 Tip
            - 소스코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지 알려주는 정보를 담고 있다.
           @SpringBootApplication
                - 1. 내장 서버(톰캣) 지원
                - 2. 스프링 MVC 내장
                    View : resources
                    Controller : @Controller , @RestController
                        service : @Service
                    Model(Dao) : @Repository
                        entity(DB table) : @Entity
                        그외 별도 : @Component
                        설정 클래스 : @Configuration
                - 3. 컴포넌트(module) 스캔 : MVC 클래스 스캔
                    동일 패키지내 혹은 하위 패키지 스캔


           @Controller
           @GetMapping

- 다른 클래스의 함수를 호출하는방법 [ 상호작용 특징 ]

	1. 해당 함수가 인스턴스(static없으면)  멤버이면
	클래스명 변수명 = new 클래스명()
	변수명.함수();
	2.해당 함수가 인스턴스 멤버이면
	new 클래스명().함수();
	3. 해당 함수가 정적(static) 멤버이면
	클래스명.함수();
	4. 해당 클래스가 싱글톤( 프로그램내 무조건 하나의 객체만 갖는 패턴 )
	클래스명.getIntance().함수();
	5. @Autowried [ IOC(제어역전) , DI(주입) ]
	    1. ( IOC )스프링 컨테이너(JVM 기준으로 만들어진 메모리 저장소) 등록 빈(객체) 된 경우
	        @Controller , @RestController @Service @Repository @Entity @Component @Configuration @bean 등등
	        - 반복적으로 사용할 클래스들의 객체가 주로 (MVC 클래스들) , DTO 등록은 X
        2. ( DI ) 스프링 컨테이너( JVM 만들어진 메모리 저장소 ) 등록된 빈(객체) 호출
            @Autowired



-----
// DAO 에게 요청하고 응답 받기
        // 1.
        ArticleDao articleDao = new ArticleDao();
        articleDao.crateArticle();
        // 2. 해당 함수가 인스턴스 멤버이면
        new ArticleDao().crateArticle();
        // 3. 해당 함수가 정적(static) 멤버이면
        ArticleDao.crateArticle();;
        // 4. 해당 클래스가 싱글톤 이면
        ArticleDao.getInstance().crateArticle();

*/
