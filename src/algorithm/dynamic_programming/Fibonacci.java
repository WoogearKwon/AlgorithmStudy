package algorithm.dynamic_programming;

import practice.Practice;

/**
 * 피보나치 수열
 * */
public class Fibonacci extends Practice {

    @Override
    public void run() {
//        runRecursive(8);
        runLoopFibonacci(8);
    }

    private void runRecursive(int n) {
        for (int i = 1; i <= 8; i++) {
            print(fibo(i) + " ");
//            print(fiboFast(i) + " ");
        }
    }

    /**
     * 피보나치 수열(재귀호출 1)
     * 시간복잡도: O(2^N)
     * */
    private int fibo(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibo(n - 2) + fibo(n - 1);
        }
    }

    int[] memo = new int[100];

    /**
     * 피보나치 수열(재귀호출 2)
     * 메모리를 사용하여 이미 한번 구한 값은 다시 구하지 않도록 수정한 피보나치 수열
     * 시간복잡도: O(N) => 문제의 개수 * 문제 1개를 푸는 시간
     * */
    private int fiboFast(int n) {
        if (n <= 1) {
            return n;
        } else {
            if (memo[n] > 0) {
                // 저장된 데이터가 있으면 리턴
                return memo[n];
            }
            memo[n] = fiboFast(n - 1) + fiboFast(n - 2);
            return memo[n];
        }
    }

    /**
     * 피보나치 수열(반복문)
     * */
    private void runLoopFibonacci(int n) {
        int num1 = 0;
        int num2 = 1;
        int sum = 1;

        for (int i = 0; i < n; i++) {
            print(sum + " ");
            sum = num1 + num2;
            num1 = num2;
            num2 = sum;
        }
    }
}
