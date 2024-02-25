package 개인연습._20240224회원게시판.medel.dao;

import org.springframework.stereotype.Component;
import 개인연습._20240224회원게시판.medel.dto.MemberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class Dao {

    // JDBC DB 연동
    // DB 인터페이스 구현
    private Connection conn; // DB 연동 결과 객체를 연결
    private PreparedStatement ps; // 기재된 SQL 에 매개변수 할당 SQL 실행
    private ResultSet rs; // select 결과 레코드 호출

    // 생성자
    public Dao(){
        try {
            // 1. JDBC 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB 서버 주소 와 연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb" , "root" , "1234");
            System.out.println("DB 연동 성공!");

        }catch (Exception e){
            System.out.println("e = " + e);
        }
    } // 생성자 end

    public boolean signUp(MemberDto memberDto){
        System.out.println("Dao.signUp");
        System.out.println("Dao.signUp(DTO) = " + memberDto);
        String sql = "insert into membership(mid , mpw , mphone) values(? , ? , ?);";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpw());
            ps.setString(3, memberDto.getMphone());

            // DB 명령문 실행
            int count = ps.executeUpdate();
            System.out.println("count = " + count);
            // 회원 가입 성공 시 DB 1을 반환 하면 성공!
            if (count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("signUp = " + e);
        }
        return false;
    }

    // 로그인 요청 처리
    public boolean signIn(MemberDto memberDto){
        System.out.println("Dao.signIn");
        System.out.println("signIn -> memberDto = " + memberDto);
        // # 특정 필드 값이 조건에 충족하는 레코드만 검색 : select 필드명1 , 필드명2 from 테이블명 where 조건식
        String sql = "select * from membership where mid = ? and mpw = ?;";
        try {
            ps = conn.prepareCall(sql);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpw());
            rs = ps.executeQuery();
            if (rs.next()){
                // 로그인한 회원 번호 가져오기 DB 1번쨰 저장 되서  1
                memberDto.setMno(rs.getInt(1));
                System.out.println("로그인한 회원번호 = " + memberDto.getMno());
                return true;
            }
        }catch (Exception e){
            System.out.println("signIn = " + e);
        }
        return false;
    }

    // 아이디와 비밀번호로 회원번호 찾기
    public int findMno(MemberDto memberDto){
        System.out.println("Dao.findMno");
        System.out.println("findMno -> memberDto = " + memberDto);
        String sql = "select * from membership where mid = ? and mpw = ?";
        try {
            ps = conn.prepareCall(sql);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpw());
            rs= ps.executeQuery();
            // 1개의 아이디가 조회 되면 조건 실행
            if (rs.next()){
                // 회원번호 Dto 에 대입
                memberDto.setMno(rs.getInt(1));
                return memberDto.getMno();
            }
        }catch (Exception e){
            System.out.println("findMno : e = " + e);
        }
        return 0;
    }




}
