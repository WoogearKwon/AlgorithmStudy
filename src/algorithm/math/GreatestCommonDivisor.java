package algorithm.math;

import practice.Practice;

/**
 * 최대 공약수 (GCD, Greatest Common Divisor)
 * */
public class GreatestCommonDivisor extends Practice {

    @Override
    public void run() {
        printGcd(30, 10);
//        printEucliedeanRecursive(32, 16);
//        printEucliedeanRecursive(65, 25);
    }

    // 가장 일반적인 방법, 시간복잡도 O(N)
    // 반복문을 끝내고 answer에 담기는 숫자가 최대공약수
    private void printGcd(int a, int b) {
        int answer = 1;
        for (int i = 2; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                answer = i;
            }
        }

        printAnswer(answer);
    }

    /**
     * 유클리드 호제법 (Eucliedean Algorithm)
     *
     * a % b = r이라고 했을 때
     * gcd(a,b) = gcd(b,r) 성립
     * 위 과정을 반복,
     * r 이 0 일때 b가 최대공약수
     * ex) gcd(24, 16) => gcd(16, 8) => gcd(8, 0) answer is 8
     * */
    // 재귀함수로 구현, 시간복잡도 O(LogN)
    private void printEucliedeanRecursive(int a, int b) {
        if (b == 0) {
            printAnswer(a);
        } else {
            printEucliedeanRecursive(b, a % b);
        }
    }

    // 반복문으로 구현
    private void printEcliedeanLoop(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        printAnswer(a);
    }
}
