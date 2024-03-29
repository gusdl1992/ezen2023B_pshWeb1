package example.day10._1example;

import lombok.*;


public class Calculator {

    private int memory;

    public int getMemory() {
        return memory;
    }

    // setter
    // synchronized 동기화 : 여러 스레드가 해당 메소드/블록을 호출 했을때 순서매기기
        // 컬렉션 프레임워크 : List( Vector ) , map( hashTable )
    public synchronized void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        }catch (Exception e ){
            System.out.println(e);
        }
        System.out.println(Thread.currentThread().getName() +" : " + this.memory );
    }
}
