package ezenweb.medel.dao;

import ezenweb.medel.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao extends Dao{

    // 1. 글쓰기 처리 [ 글쓰기를 성공 했을떄 자동 생성된 글번호 반환 , 실패시 0 ]
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);

        try {
            String sql = "insert into board(btitle , bcontent , bfile ,mno , bcno)values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count = ps.executeUpdate();
            if (count == 1){
                rs = ps.getGeneratedKeys();
                if (rs.next()){return  rs.getLong(1); }
            }

        }catch (Exception e){
            System.out.println("BoardDao 글쓰기 : e = " + e);
        }

        return 0;
    }

    // 2. 전체 글 출력 호출
    public List<BoardDto> doGetBoardViewList(int startRow , int pageBoardSize ){
        BoardDto boardDto = null;
        List<BoardDto> list = new ArrayList<>();
        System.out.println("BoardDao.doGetBoardViewList");
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no order by b.bdate desc limit ? , ?;";
            ps = conn.prepareCall(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
            rs = ps.executeQuery();
            while (rs.next()){
                boardDto = new BoardDto(
                        rs.getLong("bno") , rs.getString("btitle") ,
                        rs.getString("bcontent") , rs.getString("bfile"),
                        rs.getLong("bview") , rs.getString("bdate"),
                        rs.getLong("mno") , rs.getLong("bcno") ,
                        null , rs.getString("id") , rs.getString("img")
                );
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println("Dao 전체글 출력 e = " + e);
        }
        return list;
    }
    // 2- 2 전체 게시물 수 호출
    public int getBoardSize(){
        try {
            String sql = "select count(*) from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){return  rs.getInt(1); }
        }catch (Exception e){
            System.out.println("게시물 수 사이즈 호출e = " + e);
        }
        return 0;
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(@RequestParam int bno){ // 헷갈리면 day 11 보기
        System.out.println("BoardDao.doGetBoardView");
        BoardDto boardDto = null;
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno = ?;";
            ps = conn.prepareCall(sql);
            ps.setLong(1,bno);
            rs = ps.executeQuery();
            if (rs.next()){
                 boardDto = new BoardDto(
                    rs.getLong("bno") , rs.getString("btitle") ,
                    rs.getString("bcontent") , rs.getString("bfile"),
                    rs.getLong("bview") , rs.getString("bdate"),
                    rs.getLong("mno") , rs.getLong("bcno") ,
                    null ,  rs.getString("id") , rs.getString("img")
                );
            }
        }catch (Exception e){
            System.out.println("DAO 개별글 호출 : e = " + e);
        }
        return boardDto;
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리
}
