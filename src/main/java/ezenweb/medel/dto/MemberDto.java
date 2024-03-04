package ezenweb.medel.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberDto {

    private long no;     /* 회원번호 */
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    // private String img; 텍스트 형식
    private MultipartFile img;  // input type = "file"(MultipartFile)  첨부 파일 형식
    private String uuidFile;    //uuid+file


}
