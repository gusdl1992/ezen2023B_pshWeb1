package example.day11._2Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController // controller + ResponseBody
@RequestMapping(value = "/day11")
public class RestController3 { // HttpServletResponse 없애기

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수 http://localhost/day11/black/value
        // 2. 쿼리스트링 변수 http://localhost/day11/black?key=value

    // 1. GET : http://localhost:80/day11/white
    // @RequestMapping(value = "/day11/red" , method = RequestMethod.GET)
    @GetMapping(value = "/red")
    public String getRed(HttpServletRequest req ) throws IOException{
        System.out.println("RestController3.getRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        return sendMsg;

    }
    // 2. POST
    @PostMapping("/red")
    public Map<String , String> postRed(HttpServletRequest req ) throws IOException{
        System.out.println("RestController3.postRed");
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
    @PutMapping("/red")
    public int putRed(HttpServletRequest req ) throws IOException{
        System.out.println("RestController3.putRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }
    // 4. delete
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest req ) throws IOException{
        System.out.println("RestController3.deleteRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }


}
