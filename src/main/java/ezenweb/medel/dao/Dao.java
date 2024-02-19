package ezenweb.medel.dao;

import ezenweb.medel.dto.LoginDto;
import ezenweb.medel.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Dao {
    static int number = 0;

    ArrayList<MemberDto> memberDtos = new ArrayList<>();
    public boolean doPostSignup(MemberDto memberDto){

        System.out.println("Dao.doPostSignup");
        memberDto.setNo(++number);
        memberDtos.add(memberDto);
        System.out.println("memberDtos = " + memberDtos);
        return true;
    }
    public MemberDto doPostLogin(LoginDto loginDto){
        System.out.println("Dao.doPostLogin");
        if (memberDtos != null){
            for (int i = 0 ; i < memberDtos.size() ; i++){
                if (memberDtos.get(i).getId().equals(loginDto.getId()) && memberDtos.get(i).getPw().equals(loginDto.getPw())){
                    return memberDtos.get(i);
                }
            }
        }
        return null;
    }

}
