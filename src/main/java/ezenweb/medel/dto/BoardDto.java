package ezenweb.medel.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Builder // 생성자 단점을 보완한 라이브러리 함수.
public class BoardDto {
    long bno;           // 번호
    String btitle;      // 제목
    String bcontent;    // 내용
    String bfile;       // 첨부파일 [ 첨부파일 이름 출력용 - DB 처리 ]
    long bview;         // 조회수
    String bdate;       // 작성일
    long mno;           // 작성자
    long bcno;          // 카테고리
    MultipartFile uploadfile; // 실제 첨부 파일 DB처리X , 서버에 저장
    
    // * 전체 출력시 필드
    String mid;
    String mimg;
    
}

/*
    - 용도에 따라 다양한 DTO 가 존재할수 있다.
    - 하나의 DTO에 서로다른 용도로 사용
    
    1. 글쓰기용
    - 입력 받기 : btitle , bcontent , uploadfile , bcno
    - 서버 처리 : bno 자동 , bview 기본값 0 , bdate 기본값현재날짜 , mno로그인(*세션)
    2. 출력용
        - 출력용 : bno , btitle , bcontent , bfile , bview , bdate , mno , bcno
    3. 전체 출력용
        - 전체출력용 : bno , btitle , bcontent , bfile , bview , bdate , mno , bcno ,
                    mid , mimg;

    - 생성자 단점/규칙 : 매개변수의 순서 , 개수 => 유연성 떨어짐
    - 빌더 패턴 : @Builder
        
*/
