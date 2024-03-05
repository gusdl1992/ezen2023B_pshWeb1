package ezenweb.Service;


import ezenweb.medel.dao.BoardDao;
import ezenweb.medel.dto.BoardDto;
import ezenweb.medel.dto.BoardPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardService.doPostBoardWrite");
        // 1. 첨부파일 처리
            // 첨부파일이 존재하면
        if( !boardDto.getUploadfile().isEmpty() ){
            boardDto.setBfile(fileService.fileUpload( boardDto.getUploadfile() ) );
            if(boardDto.getBfile() == null){
                return -1; // 파일을 첨부 했는데 파일 업로드 실패 시 실패 처리
            }
        }
        return boardDao.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출
    public BoardPageDto doGetBoardViewList(int page){
        System.out.println("BoardService.doGetBoardViewList");

        // 페이지 처리시 사용할 SQL 구문 : limit 시작레코드 번호 (0) 부터 , 개수
        
        // 1. 페이지당 게시물을 출력할 개수
        int pageBoardSize = 2;

        // 2. 페이지당 게시물을 출력할 시작 레코드 번호.
        int startRow = ( page-1 ) * pageBoardSize;
        // 3. 총 페이지 수
            // 1. 전체 게시물 수
        int totalBoardSize = boardDao.getBoardSize();
            // 2. 총 페이지 수 계산
        int totalPage = totalBoardSize % pageBoardSize == 0 ?
                        totalBoardSize / pageBoardSize :
                        totalBoardSize / pageBoardSize + 1;
        // 4 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList(startRow , pageBoardSize);

        // 5 페이징 버튼 개수
            // 1. 페이지 버튼 최대 개수
        int btnSize = 5; // 5개씩
            // 2. 페이지 버튼 시작 번호
        int num = (page-1) / btnSize;
        System.out.println("num = " + num);
        int startBtn = ((page-1) / btnSize) == 0 ? 1 : num*btnSize+1 ;    // 1번 버튼
        System.out.println("startBtn = " + startBtn);
            // 3. 페이지 버튼 끝번호
        int endBtn = startBtn+btnSize-1;    // 5번 버튼
        System.out.println("endBtn = " + endBtn);
            // 만약에 페이지 버튼의 끝번호가 총 페이지 수 보다는 커질 수 없다.
        if (endBtn >= totalPage){
            endBtn = totalPage;
        }

        /*
        페이징 버튼 처리
        int btnsize = 5;
            //2.페이지 버튼 시작 번호
        int startbtn = (page-1)/btnsize*btnsize+1;
            //3 페이지 버튼 끝번호
        int endbtn = startbtn+btnsize-1;
        */

        // pageDto 구성
        BoardPageDto boardPageDto = new BoardPageDto(
                page , totalPage , startBtn , endBtn , list
        );

        return boardPageDto;
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(@RequestParam int bno){ // 헷갈리면 day 11 보기
        System.out.println("BoardService.doGetBoardView");
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리


}
