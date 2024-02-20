package example.day08;

import javax.tools.Tool;
import java.awt.*;

public class Example1 {

    /*
        운영체제는 실행중인 프로그램을 프로세스로 관리
            - 프로그램 1개당 프로세스 존재
            - 멀티 태스킹 : 두 가지 이상을 동시에 처리
                - 프로그램 1개당 멀티 프로세스가 존재 할수 있다.
                - 프로세스 1개당 멀티 스레드가 존재할 수 있다.
            - 멀티스레드 : 하나의  프로세스가 두 가지 이상의 작업을 처리 할수 있다.
        
        스레드 : 코드의 실행 흐름
            - 스레드 마다 스택 할당
            - 스레드끼리 자원 공유 안됨. ( 힙 , 메소드 영역을 통한 자원 공유 가능!)
            - 하나의 스레드가 예외 발생시 전체 스레드가 예외 발생

        멀티스레드 : 여러개의 코드 실행 흐름
            - 생성 : main 스레드가 추가 작업 스레드 생성
            - 자바는 무조건 하나의 스레드를 갖는다 Main 함수()

        CPU 코어 1개당
            ---------------------------------------------------------
            작업요청
                멀티 : 안됨X
                여러개 스레드들의 작업 순서는 하나씩 처리 ( 컴퓨터는 빠르기 때문에 동시처리 처럼 보인다. )
                작업순서 : 운영체제가 스케줄링
                자바에서 서로다른 스레드끼리의 작업순서 정하기 불가능.
                프로그램(소프트웨어) 은 자원(하드웨어) 제어권이 없다. ( 운영체제가 자원 할당 )

        JVM
        메소드 영역          스택 영역                           힙영역
        - 클래스정보         - 스레드마다 할당                  - 인스턴스 할당
        - static            - 함수실행{} 지역변수할당

    */

    // 1. 메인스레드(메인함수) 선언
    public static void main(String[] args) {
        // * java.awt : java.ui
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 1 ; i <= 5 ; i++){ // main 스레드가 for 실행
            toolkit.beep(); // 띵 소리 발생시키는 함수.
            // for 문이 5번 회전하는것보다 띵소리가 느리기 때문에 1번만 발생.
            // 메인스레드 잠시 멈추기
                // Thread.sleep( 밀리초 ) : 밀리초(1/1000초) 만큼 일시정지
                // * 일반예외처리 : 혹시나 일시정지 도중에 다른쪽 스레드가 예외 발생시킬수 있을수 있으니까.
            try {
                Thread.sleep(500);  // main 스레드를 0.5 초간 일시정지
            }catch (InterruptedException e){
                System.out.println("e = " + e);
            } // try end
        } // for e

        for (int i = 1 ; i <= 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);  // main 스레드를 0.5 초간 일시정지
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
            } // try end
        } // for end
        // ================== 단일 스레드 일때 end ======================================

        // ================== 멀티 스레드 [ 익명 ] 일때 ========================================
        // 1. Runnable 인터페이스 구현 객체 필요
            // 1. 구현 클래스
            // 2. 익명 구현체 : 인터페이스가 new 이용한 직접 추상메소드 재정의
                // new 인터페이스명(){}

        // 2. 구현객체를 Thread 생성자에 대입.
        Thread thread = new Thread(new Runnable() {
            // ============ 작업 스레드 구현 =========
            @Override
            public void run() { // 1. 작업 스레드가 실행하는 메소드
                Toolkit toolkit1 = Toolkit.getDefaultToolkit();
                for (int i = 1 ; i <= 5 ; i++){
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        System.out.println("e = " + e);
                    } // 예외 end
                } // for end
            } // run 메소드 end
            // ===== 작업 스레드 구현 end =========
        });
        thread.start();     // 2. 작업 스레드 실행;
        for (int i = 1 ; i <= 5; i++){  // [ main 스레드가 코드 실행 ]
            System.out.print("띵");
        }

        // ================== 멀티 스레드2[ 구현 클래스 ] 일때 ========================================
        // 1. Runnable 객체
        Runnable runnable = new 작업스레드();
        // 2. Thread 객체
        Thread thread1 = new Thread(runnable);
        // 3. 작업스레드 실행
        thread1.start();
        for (int i = 1 ; i <= 5; i++){  // [ main 스레드가 코드 실행 ]
            System.out.print("띵");
        }

        // ================== 멀티 스레드3 [ Thread 자식 객체 ] 일때 ========================================
        // 상속 : extends 하나의 클래스만 가능
        // 인터페이스 : implements 여러개 인터페이스

        // 자식 객체
        작업스레드2 작업스레드2 = new 작업스레드2();
        작업스레드2.start();
        // vs
        // 익명 자식 객체
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                for (int i = 1 ; i <= 5 ; i++){
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    toolkit.beep();
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        System.out.println("e = " + e);
                    } // 예외 end
                }
            }
        };
        thread2.start();
    /*
        [ 인터페이스 하나의 클래스에 여러개를 연결/구현]
         구현객체(구현체) 란 : 추상메소드를 구현한 클래스의 객체
         - 구현객체
              1. 클래스 implements 인터페이스 {}
              2. 인터페이스명 변수명 = 구현클래스 new
         - 익명 구현객체
              1. 인터페이스명 변수명 = new 인터페이스명(){ 추상메소드 재정의 }

            + Runnable 인터페이스
                1. run 메소드 재정의한다. ( 생성된 작업 스레드가 처리할 행위/메소드/함수/코드 )
                2. Thread 클래스 객체 생성 해서 start();
                        Runnable runnable = new runnable(){재정의};
                        Thread thread = new Thread( runnable );
                        thread.start();
         [ 상속 : 하나의 클래스의 하나의 클래스만 연결/상속]
         자식객체 : 부모클래스로부터 (필드/메소드) 상속/물려받은 받은 클래스의 객체
         - 자식객체
              1. 자식클래스 정의 : 클래스 extends 부모클래스{ 재정의 }
              2. 부모/자식 클래스 변수명 = new 자식클래스();
         - 익명 자식 객체
              1. 부모 변수명 = new 부모클래스(){ 재정의 };

            + Thread 클래스
                1. run 메소드 재정의 한다.
                2. Thread 클래스 객체 생성 해서 start();
                        Thread thread = new 자식클래스();
                        thread.start();
    */
    }  // m e
} // c e
