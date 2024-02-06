package example.day01.consoleMvc;

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
}
