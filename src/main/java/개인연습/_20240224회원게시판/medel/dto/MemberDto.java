package 개인연습._20240224회원게시판.medel.dto;


import lombok.*;

@AllArgsConstructor // 풀 생성자
@NoArgsConstructor // 기본 새성자
@Getter @Setter @ToString // get  set ToString
public class MemberDto {
    private int mno;
    private String mid;
    private String mpw;
    private String mphone;
    private String mdate;


    // 회원가입용 DTO 아이디 , 비밀번호 , 번호만 입력
    public MemberDto(String mid , String mpw , String mphone){
        this.mid = mid;
        this.mpw = mpw;
        this.mphone = mphone;
    }


}

/*
    mno int auto_increment ,		# 회원번호
    mid varchar(10) not null unique,  # 아이디
    mpw varchar(15) not null, 			# 비밀번호
    mphone char(13) ,				# 전화번호
    mdate datetime default now() ,



*/