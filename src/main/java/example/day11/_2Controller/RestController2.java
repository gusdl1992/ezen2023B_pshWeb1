package example.day11._2Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController2 { // HttpServletResponse 없애기

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수 http://localhost/day11/black/value
        // 2. 쿼리스트링 변수 http://localhost/day11/black?key=value

    // 1. GET : http://localhost:80/day11/white
    @RequestMapping(value = "/day11/white" , method = RequestMethod.GET)
    @ResponseBody   // 응답 contentType   String ----> text/plain
    public String getBlack(HttpServletRequest req ) throws IOException{
        System.out.println("RestController2.getBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        return sendMsg;

    }
    // 2. POST
    @RequestMapping(value = "/day11/white" , method = RequestMethod.POST)
    @ResponseBody   // 응답 contentType   String ----> application/json
    public Map<String , String> postBlack(HttpServletRequest req ) throws IOException{
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 배열 사용
        // String[] strArray = new String[2];
        // strArray[0] = sendMsg; strArray[1] = "클라이언트";

        // 리스트 사용
        // List<String> strArray = new ArrayList<>();
        // strArray.add(sendMsg); strArray.add("클라이언트");

        // 맵 사용
        Map<String , String> strArray = new HashMap<>();
        strArray.put(sendMsg , "클라이언트");
        // 응답
        return strArray;
    }

    // 3. PUT
    @RequestMapping(value = "/day11/white" , method = RequestMethod.PUT)
    @ResponseBody   // 응답 contentType   int ----> application/json
    public int putBlack(HttpServletRequest req ) throws IOException{
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }
    // 4. delete
    @RequestMapping(value = "/day11/white" , method = RequestMethod.DELETE)
    @ResponseBody   // 응답 contentType   boolean ----> application/json
    public boolean deleteBlack(HttpServletRequest req ) throws IOException{
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }


}
