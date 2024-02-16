package example.day07._1트리컬렉션;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Example {
    
    /*
        검색 강화시킨 컬렉션
            - TreeSet , TreeMap
            - 이진 트리 : 하나의 노드(뿌리) 시작해서 각 노드에 최대 2개의 노드 연결
            - 부모 노드의 객체와 비교 (낮은것은) 왼쪽 자식 (높은것은) 오른쪽 자식
            - 검색에 좋은점??? 정렬이 되었기 때문에 데이터를 찾을때 유용하다.
            - 정렬을 하면 검색이 빠르다. 가운데를 쪼개서 원하는 값 찾기 가능!
            - 기본값은 오름차순
            
        스택 / 큐 컬렉션
            스택 : 후입선출
                사용되는 용도 : ctrl+z ( 뒤로가기 ) , JVM 스택 영역
            큐   : 선입선출
                사용되는 용도 : 인쇄( 먼저 인쇄한 순서대로 ) , 스레드풀( 동기화 = 요청순서 ) , 식당 웨이팅
    */

    public static void main(String[] args) {

        // 1. TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();

        // 2. TreeSet 컬렉션 객체에 객체 추가
        scores.add(87);
        scores.add(98);
        scores.add(75);
        scores.add(95);
        scores.add(80);

        System.out.println("scores = " + scores);
        /*
            컬렉션 프레임워크 : 널리 알려진 자료구조 기반으로 미리 만들어진 클래스/인터페이스들
                자료구조 : 자료(데이터)를 저장하는 방법론
            이진 트리 : 여러 자료구조 중에 하나의 방법

        
         
        */

        // 3. 순회
        for(Integer i : scores){ System.out.print("   i = " + i); }
        System.out.println();
        scores.forEach(i -> System.out.print("  i = " + i));
        System.out.println();

        // 4. HashSet 보다 추가적인 메소드 제공

        System.out.println("가장 낮은 점수 : "  + scores.first() );
        System.out.println("가장 높은 점수 : " + scores.last() );
        System.out.println("95점 아래 점수 : " + scores.lower(95) );
        System.out.println("95점 위의 점수 : " + scores.higher(95) );
        System.out.println("95점 이거나 바로 아래 점수 : " + scores.floor(95) );
        System.out.println("85점 이거나 바로 위의 점수 : " + scores.ceiling(85) );

        // 5. 내림차순
        NavigableSet<Integer> descending = scores.descendingSet();
        System.out.println("descending = " + descending);
        System.out.println("scores 내림차순 정리 = " + scores.descendingSet());

        // 6 . 범위 검색 ( 80 <= ) 80이상
        System.out.println("scores.tailSet(80 , true) : " + scores.tailSet(80 , true));
        // (80 <= <90) 80 ` 89 사이
        System.out.println("scores.subSet(80 , true , 90, false) : " + scores.subSet(80 , true , 90, false));



    } // m e
} // c e
