package example.day09;



public class SumThread extends Thread{
    private long sum;

    public SumThread() {}

    public SumThread(long sum) {
        this.sum = sum;
    }

    // * 1 ~ 100 까지 누적합을 구하는 함수
    @Override
    public void run() {
        for (int i = 0 ; i<=100 ; i++){
            sum += i;
        }
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "SumThread{" +
                "sum=" + sum +
                '}';
    }

}
