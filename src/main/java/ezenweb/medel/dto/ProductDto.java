package ezenweb.medel.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder // 빌더 패턴
public class ProductDto {

    private int pno;            // -- 제품번호
    private String pname;       // -- 제품 이름
    private int pprice;         // -- 제품 가격
    private String pcontent;    // -- 제품 설명
    private byte pstate;        // -- 제품 상태
    private String pdate;       // -- 제품 등록일
    private String plat;        // -- 제품 위치 위도
    private String plng;        // -- 제품 위치 경도
    private int mno;            // -- 제품 등록 회원
    


    // - 등록할떄 이미지
    private List<MultipartFile> uploadFiles;
    // - 출력할떄 이미지
    private List< String > pimg;
    // - 출력할때 작성자 번호가 아닌 작성자 아이디
    private String min;

    // + 1. 제품 등록 [ pname , pprice , pcontent , plat , plng , mno(세션) , uploadFiles  ]

    
    // + 2. 제품 지도 출력 [ pno , pname , pprice , pstate  , plat , plng  ]

    // + 3. 제품 지도에서 마커 클릭시 상세 출력 [  pno , pname , pprice , pcontent, pstate , pdate , plat , plng , mno , pimg]

}
