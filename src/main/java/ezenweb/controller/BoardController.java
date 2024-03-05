package ezenweb.controller;


import ezenweb.Service.BoardService;
import ezenweb.Service.MemberService;
import ezenweb.medel.dto.BoardDto;
import ezenweb.medel.dto.BoardPageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board") // 공통 url
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    // 1. 글쓰기 처리                          /board/write.do     POST          DTO
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardController.doPostBoardWrite");
        // 1. 현재 로그인된  세션 ( 톰캣서버(자바프로그램) 메모리(JVM) 저장소  ) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if( object == null ){return -2;}

        // 2. 형변환
        String mid = (String)object;

        // 3. mid 를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();

        // 4. 작성자 번호 대입
        boardDto.setMno(mno);

        return boardService.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출                     /board/do               GET          X , 페이징처리 , 검색
    @GetMapping("/do") // (쿼리스트링)매개변수 : 현재페이지
    @ResponseBody
    public BoardPageDto doGetBoardViewList(int page){
        System.out.println("BoardController.doGetBoardViewList");
        System.out.println("page = " + page);
        return boardService.doGetBoardViewList(page);
    }

    // 3. 개별 글 출력 호출                     /board/view.do      GET           게시물번호
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){ // 헷갈리면 day 11 보기
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리                         /board/update.do     put          DTO

    // 5. 글 삭제 처리                         /board/delete.do     delete       게시물번호



    // ================ 머스테치는 컨트롤에서 뷰 반환.  ======================= //

    // 1. 글쓰기 페이지 이동                    /board/write        GET
    @GetMapping("/write")
    public String getBoardWrite(){
        System.out.println("BoardController.getBoardWrite");
        return "ezenweb/board/write";
    }
    // 2. 게시판 페이지 이동                    /board              GET
    @GetMapping("")
    public String getBoard(){
        System.out.println("BoardController.getBoard");
        return "ezenweb/board/board";
    }
    // 3. 게시판 상세 페이지 이동                /board/view         GET
    @GetMapping("/view")
    public String getBoardView(int bno){
        return "ezenweb/board/view";
    }

    // 4. 글 수정 페이지 이동                   /board/update       GET



} // c e