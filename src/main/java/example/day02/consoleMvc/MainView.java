package example.day02.consoleMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {

    private Scanner scanner = new Scanner(System.in);
    private TodoController todoController = new TodoController();

    public void home(){
        while (true){
            doGet(); // 할일 목록 출력
            System.out.print("1. 할일등록 : 2.할일상태변경 3.할일삭제 : > ");
            try {
                int ch = scanner.nextInt();
                if(ch == 1 ){ doPost(); } // 할일등록
                if(ch == 2 ){ doPut(); } // 할일상태 수정
                if(ch == 3 ){ doDelete(); } // 할일삭제
            }catch (Exception e){
                System.out.println("입력이 잘못 되었습니다.");
                System.out.println(e);
                scanner = new Scanner(System.in); // 스캐너 초기화
            }

            

        } // w e
    } // h e

    // 2. 할일등록 메소드
    public void doPost(){
        System.out.print("할일 내용 : ");
        String content = scanner.next();
        System.out.println();
        System.out.print("마감일[yyy-mm-dd] : ");
        String deadline = scanner.next();
        
        // 2. 객체화
        TodoDto todoDto = new TodoDto();
        todoDto.setContent(content);
        todoDto.setDeadline(deadline);
        
        // 3. 컨트롤 에게 요청 응답 받기
        boolean result = todoController.doPost(todoDto);
        
        // 4. 응답결과 출력하기
        System.out.println(result);
        
    }
    // 3 .할일목록 출력 메소드
    public void doGet(){ 
        // 1. 입력받기 - 전체 출력이라서 조건이 없음.
        // 2. 객체화
        // 3. 컨트롤 에게 요청 응답 받기

        ArrayList<TodoDto> result = todoController.doGet();

        // 4. 응답결과 출력하기
        for (int i = 0 ; i < result.size(); i++){
            // i 번쨰 dto 를 호출
            TodoDto todoDto = result.get(i);
            System.out.printf("%2s %10s %10s %10s \n",
                    todoDto.getId(),
                    todoDto.getDeadline(),
                    todoDto.isState(),
                    todoDto.getContent()
                    );
        }
    }

    // 4. 할일 수정
    public void doPut(){
        // 1. 입력받기
        System.out.print("수정할 todo id : ");
        int id = scanner.nextInt();
        System.out.print("수정할 state : ");
        boolean state = scanner.nextBoolean();
        // 2. 객체화
        TodoDto todoDto = new TodoDto();
        todoDto.setId(id);
        todoDto.setState(state);
        
        // 3. 컨트롤러에게 요청 응답 받기
        boolean result = todoController.doPut(todoDto);
        // 4. 응답결과 출력하기
        System.out.println(result);

    } // m e

    // 5. 할일 삭제
    public void doDelete(){
        // 1. 입력받기
        System.out.print("삭제할 todo id :");
        int id = scanner.nextInt();
        // 2. 객체화 ( 선택사항 * 데이터 전달이 1개라서 )
        // 3. 컨트롤러에게 요청 응답 받기
        boolean result = todoController.doDelete(id);
        // 4. 응답결과 출력하기
        System.out.println(result);

    }
}
