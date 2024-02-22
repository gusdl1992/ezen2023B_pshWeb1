package example.day10._3Servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 자바 회사에서 윕개발을 위한 HTTP 웹 통신 클래스 : HttpServlet
    // : MVC 패턴에서는 주로 controller 역할
    // 서블릿 선언 하는 방법
    // 1. 해당 클래스에 HttpServlet 상속 받는다.
    // 2. 해당 클래스에 @WebServlet("HTTP 식별주소") 어노테이션을 주입해서 web.xml 에 등록한다.
    // 3. HttpServlet 가 제공하는 메소드를 오버라이딩 : init() , service() , doXXX , destroy 

/*
     서블릿 실행 구동 순서
     1. 클라이언트(브라우저) HTTP 요청이 (AWS(톰캣서버)) 에 들어온다.
     2. 서블릿컨테이너에 요청받은 서블릿이 있는지 없는지 판단.
     3. 없으면 init() 메소드 실행한 서블릿 생성
     4. 있으면 또는 생성 했으면스레드(작업스레드) 할당 받아 이동
     5. service() 실행 하고 HTTP 요청 method 에 따른 메소드로 이동
     6. doxxx 메소드 실행 요청(Request)
        - HTTP 관련된 정보를 요청할수 있는 기능을 가지고 있다
     7. doxxx 메소드 종료될떄 응답(HttpServletRequest)
        - HTTP 관련된 정보를 응답할수 있는 기능을 가지고 있다
     -------- 다음 요청이 올때까지. 서블릿이 사라지진않음
     1 -> 2-> 4 -> 5-> 6
     -------- 서버가 종료 되면 destroy() 실행되면서 안전하게 서블릿 제거  
*/
@WebServlet("/Test-servlet")   // web.xml 등록
public class TestServlet extends HttpServlet {

    // HttpServlet 클래스로부터 상속받으면 다양한 메소드 사용


    @Override   // 1. [ 최초 요청 1번 실행 ] 해당 서블릿 객체(서버당 1개 )가 생성되었을때 실행되는 메소드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("TestServlet.init");
        // 주로 첫시작 셋팅 , DB 연동
        super.init(config);
    }

    @Override   // 2. [ 요청 마다 실행 ] 해당 서블릿 으로부터 HTTP 서비스 실행 되었을때 실행 되는 메소드
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.service");

        super.service(req, resp);
    }

    @Override   // 3. [ HTTP method 따라 실행 ] HTTP 서비스 요청중에 HTTP method 방식이 get 이면 실행되는 메소드
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
        resp.setContentType("text/html"); //보내줄 데이터의 타입을 정함, 현재는 html
        resp.getWriter().println("Get"); //get.Writer().println("talend에 전송할 값, html이 출력됨")
        
        // DAO 호출 
        
        // super.doGet(req, resp); 이 있으면 값을 덮어 씌어지기 때문에 생략
    }

    @Override   // [ HTTP method 따라 실행 ]
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPost");
        resp.setContentType("text/html"); //보내줄 데이터의 타입을 정함, 현재는 html
        resp.getWriter().println("POST"); //get.Writer().println("talend에 전송할 값, html이 출력됨")

        // super.doPost(req, resp); 이 있으면 값을 덮어 씌어지기 때문에 생략
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPut");
        resp.setContentType("text/html"); //보내줄 데이터의 타입을 정함, 현재는 html
        resp.getWriter().println("Put"); //get.Writer().println("talend에 전송할 값, html이 출력됨")
        // super.doPut(req, resp);  이 있으면 값을 덮어 씌어지기 때문에 생략
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doDelete");
        resp.setContentType("text/html"); //보내줄 데이터의 타입을 정함, 현재는 html
        resp.getWriter().println("Delete"); //get.Writer().println("talend에 전송할 값, html이 출력됨")
        // super.doDelete(req, resp); 이 있으면 값을 덮어 씌어지기 때문에 생략
    }

    @Override   // 4. [ 서버가 종료될때 1번 실행 ] 해당 서블릿 객체가 삭제 되었을때 실행되는 메소드
    public void destroy() {
        System.out.println("HelloServlet.destroy");
        super.destroy();
    }
}
