package example.day11._2Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RestController1 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수 http://localhost/day11/black/value
        // 2. 쿼리스트링 변수 http://localhost/day11/black?key=value

    // 1. GET : get localhost:80/day11/black
    @RequestMapping(value = "/day11/black" , method = RequestMethod.GET)
    public void getBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.getBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("안녕[클라이언트]");
        resp.getWriter().println( sendMsg );

    }
    // 2. POST
    @RequestMapping(value = "/day11/black" , method = RequestMethod.POST)
    public void postBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println(sendMsg);
    }

    // 3. PUT
    @RequestMapping(value = "/day11/black" , method = RequestMethod.PUT)
    public void putBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println(sendMsg);
    }
    // 4. delete
    @RequestMapping(value = "/day11/black" , method = RequestMethod.DELETE)
    public void deleteBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException{
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println(sendMsg);
    }


}
