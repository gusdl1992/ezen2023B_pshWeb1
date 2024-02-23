package 개인연습;

import java.util.Queue;
import java.util.LinkedList;

public class test{
    public static void main(String[] args) {
        // 큐 선언
        Queue<String> queue = new LinkedList<>();

        // 큐에 문자열 추가
        queue.add("One");
        queue.add("Two");
        queue.add("Three");

        // 큐에서 요소 제거 및 반환
        String removedElement = queue.remove();
        System.out.println("Removed element: " + removedElement);

        // 큐의 맨 앞 요소 확인 (제거하지 않음)
        String peekedElement = queue.peek();
        System.out.println("Peeked element: " + peekedElement);

        // 큐가 비어 있는지 확인
        boolean isEmpty = queue.isEmpty();
        System.out.println("Is queue empty: " + isEmpty);

        // 큐의 크기 확인
        int size = queue.size();
        System.out.println("Queue size: " + size);
    }
}
