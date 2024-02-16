package example.day07._1트리컬렉션;

import java.util.TreeSet;

public class Example3 {
    // implements : 구현하다 ( 인터페이스의 추상메소드 )


    public static void main(String[] args) {

        // 1. TreeSet 컬렉션 생성
            // - 필수 :  정렬 기준 필요! ( Integer , String , Double  등 타입 미리 정의 되어있다. )
        TreeSet<Person> treeSet = new TreeSet<>();

        // 2. 객체 저장
        treeSet.add(new Person("홍길동" , 45));
        treeSet.add(new Person("김자바" , 25));
        treeSet.add(new Person("박지원" , 31));
        System.out.println("treeSet = " + treeSet);
        // cannot be cast to class java.lang.Comparable 오류 발생. DTO 에 Comparable 인터페이스 구현 필요.


        String str = "유재석";
        
        System.out.println(str.compareTo("유재석"));
        System.out.println(str.compareTo("강호동"));
        System.out.println(str.compareTo("신동엽"));



    }

    }





