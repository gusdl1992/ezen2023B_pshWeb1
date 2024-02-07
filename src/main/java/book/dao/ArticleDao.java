package book.dao;

import book.dto.ArticleForm;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component // 스프링 컨테이너에 해당 클래스 빈(객체) 등록
@Slf4j // 간단한 로그 처리
public class ArticleDao {

    // ----------- JDBC DB 연동 -------------//
    // 1. DB 연동 필요한 인터페이스 필드 선언
    private Connection conn; // DB 연동 결과 객체를 연결 , 기재된 SQL Statement 객체 반환
    private PreparedStatement ps;  // 개재된 SQL 에 매개변수 할당 , SQL 실행
    private ResultSet rs;           // select 결과 여러개 레코드를 호출

    public ArticleDao(){
        try {
            // 1. MYSQL 회사의 JDBC 관련된 객체를 JVM 에 로딩한다. 불러오기.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동된 결과의(구현체) 객체를 Connection 인터페이스에 대입한다.
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root" , "1234"
            );

        }catch (Exception e){
            System.out.println("ArticleDao.ArticleDao");
            System.out.println("DB연동 = " + e);


        }

    }

    public boolean crateArticle(ArticleForm form){
        System.out.println("ArticleDao.crateArticle");
        System.out.println("form = " + form);
        try { // 0. try catch 작성
            // 1.
            String sql ="insert into article(title , content) values(? , ?)";
            // 2.
            ps = conn.prepareStatement(sql);
            // 3.
            ps.setString(1,form.getTitle());
            ps.setString(2, form.getContent());
            // 4.
            int count = ps.executeUpdate();
            // 5.
            if (count == 1){ return true; }
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    
}
