package 개인연습.model.dao;


import ezenweb.medel.dto.LoginDto;
import ezenweb.medel.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Dao {

    // ---------- JDBC DB연동 ----------//
    // 1. DB연동 필요한 인터페이스( 구현객체 => 각 회사(mysql com.mysql.cj.jdbc패키지내 Driver클래스 ) ) 필드 선언
    private Connection conn; // DB연동 결과 객체를 연결 , 기재된 SQL Statement객체 반환.
    private PreparedStatement ps;  // 기재된 SQL에 매개변수 할당 , SQL 실행
    private ResultSet rs;          // select 결과 여러개 레코드를 호출
    public Dao(){         // db연동를 생성자에서 처리
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
            System.out.println("DB 연동 성공!");
        }catch (Exception e ){   System.out.println(e); }
    }

    // 1.=========== 회원가입 처리 요청 ===============

    public boolean doPostSignup( MemberDto memberDto ){
        System.out.println("Dao.doPostSignup");
        System.out.println("DB : doPostSignup = " + memberDto);
        String sql = "insert into testmember(id , pw, name, email, phone, img) values(? , ? , ? ,? ,? ,?  )";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            ps.setString(6, null);

            int count = ps.executeUpdate();
            System.out.println("count = " + count);
            // 회원가입 성공 하면 true 리턴
            if (count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("doPostSignup : e  = " + e);
        }
        // 회원가입 실패 시 false 리턴
        return false;
    }

    // 2. =========== 로그인 처리 요청 ===============
    public boolean doPostLogin( LoginDto loginDto ){
        String sql = "select * from testmember where id = ? and pw = ?;";
        System.out.println("DB -> loginDto = " + loginDto);
        try {
            ps = conn.prepareCall(sql);
            ps.setString(1, loginDto.getId());
            ps.setString(2, loginDto.getPw());
            rs = ps.executeQuery();
            if (rs.next()){ // 1개의 아이디 조회 예정 if 로 next 한번 처리
                loginDto.setNo(rs.getInt(1));
                loginDto.setId(rs.getString(2));
                loginDto.setPw(rs.getString(3));
                System.out.println("DB+rs -> loginDto = " + loginDto);
                return true;
            }
        }catch (Exception e){
            System.out.println("doPostLogin e = " + e);
        }
        return false;
    }

    // 3. =========== 회원번호 찾기 ===============
    public int doFind(LoginDto loginDto){
        String sql = "select * from testmember where id = ? and pw = ?;";
        System.out.println("doFind -> loginDto = " + loginDto);
        try {
            ps = conn.prepareCall(sql);
            ps.setString(1, loginDto.getId());
            ps.setString(2, loginDto.getPw());
            rs = ps.executeQuery();
            if (rs.next()){ // 1개의 아이디 조회 예정 if 로 next 한번 처리
                loginDto.setNo(rs.getInt(1));
                loginDto.setId(rs.getString(2));
                loginDto.setPw(rs.getString(3));
                System.out.println("doFind+rs -> loginDto = " + loginDto);
                return loginDto.getNo();
            }
        }catch (Exception e){
            System.out.println("doPostLogin e = " + e);
        }
        return 0;
    }

}
