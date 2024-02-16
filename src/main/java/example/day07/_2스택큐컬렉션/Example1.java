package example.day07._2스택큐컬렉션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Example1 {

    public static void main(String[] args) {

        // 1. 스택 컬렉션 [ Vector 상속 받음 ]
        Stack<Integer> coinBox = new Stack<>();
        
        // 2. 동전 넣기 = 스택 삽입 = push
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(10);
        System.out.println("coinBox = " + coinBox);
        // 3. 동전 빼기 = 스택 빼기 = pop()
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);


        // 1. queue 컬렉션 생성
        Queue<String> messageQueue = new LinkedList<>();

        // 2. 메시지 넣기
        messageQueue.offer("안녕 홍길동");
        messageQueue.offer("안녕 신용권");
        messageQueue.offer("안녕 김자바");

        // 3. 메세지 출력
        System.out.println(messageQueue.poll() );
        System.out.println(messageQueue.poll() );
        System.out.println(messageQueue.poll() );
        

    }
}
