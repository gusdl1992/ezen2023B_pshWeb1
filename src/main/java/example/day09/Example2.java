package example.day09;

public class Example2 {

    public static void main(String[] args) {

        // 1. p.604 다른 스레드의 종료를 기다리기

        // 1. 스레드 객체 생성
        SumThread sumThread = new SumThread();
        // 2. 스레드 실행
        sumThread.start();

        // main 스레드에게 작업 스레드가 끝날때 까지 기다리게 하기
        try {
            sumThread.join();   // main 스레드와 sumThread 스레드가  JOIN
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }

        // 3. 작업스레드가 run() 메소드를 처리하기 전에 아래 실행문 처리.
        System.out.println("sumThread.getSum() = " + sumThread.getSum()); // 0


        // 2. p.606 다른 스레드에게 양보하기

        // 1. 작업스레드 2개 객체 생성
        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");

        // 2. 각 스레드 실행
        workThreadA.start();
        workThreadB.start();
        
        // 3. 5초 뒤에 A 작업 스레드의 작업을 양보하기
        try { Thread.sleep(5000); }
        catch (Exception e){System.out.println("e = " + e);}
        workThreadA.work = false;

        // 4. 10초 뒤에 A 작업 스레드의 작업을 되돌리기
        try { Thread.sleep(10000); }
        catch (Exception e){System.out.println("e = " + e);}
        workThreadA.work = true;
        

    } //  m e
}
