package example.day02.consoleMvc;

import java.util.ArrayList;

public class TodoController {
    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 메소드
    public boolean doPost(TodoDto todoDto){

        return todoDao.doPost(todoDto);
    }
    // 3 .할일목록 출력 메소드
    public ArrayList<TodoDto> doGet(){

        return todoDao.doGet();
    }

    // 4. 할일 수정
    public boolean doPut(TodoDto todoDto){

        return todoDao.doPut(todoDto);
    }

    // 5. 할일 삭제
    public boolean doDelete(int id){

        return todoDao.doDelete(id);
    }
}
