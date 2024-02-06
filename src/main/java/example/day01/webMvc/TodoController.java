package example.day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController // 해당 클래스를 스프링 MVC 환경에 등록

public class TodoController {
    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 메소드
    @GetMapping("/todo/post.do")
    public boolean doPost(TodoDto todoDto){

        return todoDao.doPost(todoDto);
    }
    // JS[외부] 가 JAVA 에게 요청하는 경로
    // http://localhost:80/todo/post.do?content=안녕하세요&deadline=2024-02-05

    // 3 .할일목록 출력 메소드
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){

        return todoDao.doGet();
    }
    // JS[외가] 가 JAVA 에게 요청하는 경로
    // http://localhost:80/todo/get.do

    // 제출 : 192.168.17.94:80
}
