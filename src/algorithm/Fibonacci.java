package algorithm;

import practice.Practice;

/**
 * 피보나치 수열
 * */
public class Fibonacci extends Practice {

    @Override
    public void run() {

        for (int i = 1; i <= 8; i++) {
            println(fibo(i));
        }
    }

    // 입력받은 n 만큼 출력하는 함수
    private int fibo(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibo(n - 2) + fibo(n - 1);
        }
    }
}
