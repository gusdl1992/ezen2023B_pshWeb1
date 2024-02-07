package example.day03.webMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


// *스프링에게 해당 클래스가 컨트롤 이라는걸 알려야 한다.
@RestController // 스프링 컨테이너[ 스프링 관리하는 메모리 공간 ] 에 빈(객체) 등록 * IOC
                // IOC 제어역전 기법 : 개발자가 객체 관리X , 스프링이 대신 관리
@Slf4j
public class TodoController {

    @Autowired // 스프링 컨테이너의 빈 찾아서 주입 // 전제조건 :  빈이 등록이 되었을떄 // 클래스가 @Component
    private TodoDao todoDao;


    // 2. 할일등록 메소드
    @PostMapping("/todo/post.do")
    public boolean doPost(TodoDto todoDto){

        return todoDao.doPost(todoDto);
    }

    // 3 .할일목록 출력 메소드
    @GetMapping("/todo/get.do") // http://localhost/todo/get.do
    public ArrayList<TodoDto> doGet(){

        return todoDao.doGet();
    }

    // 4. 할일 수정
    @PutMapping("/todo/put.do")
    public boolean doPut(TodoDto todoDto){

        return todoDao.doPut(todoDto);
    }

    // 5. 할일 삭제
    @DeleteMapping("/todo/delete.do")
    public boolean doDelete(int id){

        return todoDao.doDelete(id);
    }
}
