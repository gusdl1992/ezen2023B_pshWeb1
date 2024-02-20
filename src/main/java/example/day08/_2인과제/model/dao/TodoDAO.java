package example.day08._2인과제.model.dao;

import example.day08._2인과제.model.dto.TodoDTO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class TodoDAO {
    private Connection conn; // DB연동 결과 객체를 연결 , 기재된 SQL Statement객체 반환.
    private PreparedStatement ps;  // 기재된 SQL에 매개변수 할당 , SQL 실행
    private ResultSet rs;          // select 결과 여러개 레코드를 호출
    public TodoDAO(){         // db연동를 생성자에서 처리
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
        }catch (Exception e ){   System.out.println(e); }
    }
    public boolean write(TodoDTO todoDTO){
        try {
            String sql = "insert into todo2(content) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,todoDTO.getContent());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public ArrayList<TodoDTO> allview(){
        try {
            ArrayList<TodoDTO> result = new ArrayList<>();
            String sql= "select * from todo2";
            ps = conn.prepareStatement(sql);
            rs =ps.executeQuery();
            while (rs.next()){
                result.add(new TodoDTO(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
            }
            for(TodoDTO i : result){
                System.out.println(i);
            }
            return result;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public boolean rework(int no){
        try {
            String sql="select state from todo2 where no= ?";
            boolean result = false;
            ps= conn.prepareStatement(sql);
            ps.setInt(1,no);
            rs= ps.executeQuery();
            if(rs.next()){
                result = rs.getBoolean(1);
            }
            if(result){
                sql = "update todo2 set state = false where no = ?";

            }
            else {
                sql = "update todo2 set state = true where no = ?";
            }

            ps= conn.prepareStatement(sql);
            ps.setInt(1,no);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean del(int no){
        try {
            String sql = "delete from todo2 where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean update(TodoDTO todoDTO){
        try {
            String sql = "update todo2 set content = ? where no = ? ";
            ps= conn.prepareStatement(sql);
            ps.setInt(2,todoDTO.getNo());
            ps.setString(1,todoDTO.getContent());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
