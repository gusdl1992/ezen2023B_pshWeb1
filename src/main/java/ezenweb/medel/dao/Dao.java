package ezenweb.medel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;

    public Dao(){ // DB 연동
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
            System.out.println("DB 연동 성공");
        }catch (Exception e ){   System.out.println("DB : e ->" + e ); }

    }

}
