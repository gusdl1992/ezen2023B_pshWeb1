package example.day07._1트리컬렉션;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class Person implements Comparable<Person>{

    public String name;
    public int age;

//    오버라이딩 나이 기준
//    @Override
//    public int compareTo(Person o) {
//        // * 같으면 0 , 주어진 객체보다 적으면 -1 , 주어진 객체보다 많으면 1
//        if(this.age < o.age)return  -1;
//        else if(this.age == o.age) return 0;
//        else return 1;
//    }

    // 오버라이딩 이름 기준
    @Override
    public int compareTo(Person o) {
        // name 이름 정렬 ( String 클래스이므로 정렬기준 이미 구현  )
        return this.name.compareTo(o.name);
    }
}
/*
    // String 문자 크기 비교 ( 이미 String 에 오버라이딩 되어 있다. )
        public int compareTo(String anotherString) {
        byte v1[] = value;
        byte v2[] = anotherString.value;
        byte coder = coder();
        if (coder == anotherString.coder()) {
            return coder == LATIN1 ? StringLatin1.compareTo(v1, v2)
                                   : StringUTF16.compareTo(v1, v2);
        }
        return coder == LATIN1 ? StringLatin1.compareToUTF16(v1, v2)
                               : StringUTF16.compareToLatin1(v1, v2);
     }



*/
