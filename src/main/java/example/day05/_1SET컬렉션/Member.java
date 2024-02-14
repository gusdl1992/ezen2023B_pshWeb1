package example.day05._1SET컬렉션;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class Member {

    public String name;
    public int age;


    // hashCode , equals => 메소드의 주인은 Object
        // 힙 가상주로를 int 값이 아닌 새로운값 정의

    @Override
    public int hashCode() {
        return name.hashCode() + age;   // 이름 과 나이 조합
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof Member  ){   // 매개변수의 객체가 Member 타입이면
            Member target = (Member)obj; // 왜 ??? Member 객체 필드 사용하려고
            return target.name.equals(this.name) && (target.age == this.age);   // 이름 과 나이로 중복검사
        }
        return false;
    }

    // toString => 메소드의 주인은 Object
        // - 객체의 주소를 반환
        // 개발자가 주소 작업[X] 단 구조는 알아야 한다.
        // 오버라이딩 : 주소 반환 대신 필드(정보) 로 반환


}
