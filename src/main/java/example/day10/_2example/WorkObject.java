package example.day10._2example;

public class WorkObject {

    // 1. 메소드
    public synchronized void methodA(){
        // 1. 현재 스레드 객체 호출 : .currentThread()
        // 2. 스레드의 이름 호출 : .getName
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " : methodA 작업 실행");
        notify();   // 다른 스레드를 실행 대기 상태로
        try {
            wait(); // 현재 스레드를 일시 정지 상태로
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public synchronized void methodB(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " : methodB 작업 실행");
        notify();
        try {
            wait();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }



}
