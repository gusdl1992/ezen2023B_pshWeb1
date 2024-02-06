package example.day02.webMvc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 데이터 접근 객체 : db 에 접근과 sql ( 비지니스 로직 )역할
public class TodoDao {
    
    // 1. 필드
    private Connection conn;        // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행 , 매개변수 인터페이스
    private ResultSet rs;           // SQL 실행 결과를 호출하는 인터페이스
    // 2. 생성자 DB 연동 코드
    public TodoDao(){
        try {
            //  JDBC 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb" ,
                    "root" , "1234");
            System.out.println("DB success");
        } catch (Exception e) {
            System.out.println("DB fail : " + e);
        }

    }// 생성자 end
    
    // 3. 메소드

    // 2. 할일등록 메소드
    public boolean doPost(TodoDto todoDto){
        try {
            // 1. SQL 작성
            String sql = "insert into todo(content , deadline) values(? , ? )";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setString(1 , todoDto.getContent());
            ps.setString(2, todoDto.getDeadline());
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실행 결과
            if(count == 1){return true;}
            // 6. 함수 리턴
        }catch (Exception e){
            System.out.println(e);
        }


        return false;
    }

    // 3 .할일목록 출력 메소드
    public ArrayList<TodoDto> doGet(){
        // 0. 반환할 dotoList 객체
        ArrayList<TodoDto> list = new ArrayList<>();
        // 레코드 1개 == TodoDto 1개
        // 레코드 여러개 == List<TodoDto>;

        try {
            // 1. SQL 작성
            String sql = "select * from todo";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            // 4. SQL 실행
            rs = ps.executeQuery();
            // 5. SQL 실행 결과
            while (rs.next()){
                // next() 레코드 이동 . 없으면 false 있으면 true
                // 레코드 1개당 --> dto 1개
                TodoDto todoDto = new TodoDto(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getString("deadline"),
                        rs.getBoolean("state")
                );
                // while 문 끝나면 dto 사라져.. while 문 밖에 있는 객체로 이동
                list.add(todoDto);
            } // w e
            // 6. 함수 리턴

        }catch (Exception e){
            System.out.println(e);
        }

        return list; // 리스트 반환
    }// me

    // 4. 할일 수정
    public boolean doPut(TodoDto todoDto){
        try {
            // 1. SQL 작성
            String sql = "update todo set state = ? where id = ? ";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setBoolean(1, todoDto.isState());
            ps.setInt(2,todoDto.getId());
            // 4. SQL 실행
            int count = ps.executeUpdate(); // update 실패해도 오류 발생 X sql 실행 후 반영된 레코드 수 반환
            // 5. SQL 실행 결과
            if(count == 1){return true; }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    } // m  e

    // 5. 할일 삭제
    public boolean doDelete(int id){
        try {
            // 1. SQL 작성
            String sql = "delete from todo where id = ?";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setInt(1,id);
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실행 결과
            if (count == 1){return true;}
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    } // m e
    
} //  c e
