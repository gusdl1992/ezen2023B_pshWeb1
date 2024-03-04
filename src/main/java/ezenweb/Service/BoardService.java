package ezenweb.Service;


import ezenweb.medel.dao.BoardDao;
import ezenweb.medel.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(@RequestParam int bno){ // 헷갈리면 day 11 보기
        System.out.println("BoardService.doGetBoardView");
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리


}
