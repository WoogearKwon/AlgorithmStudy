package algorithm.dynamic_programming;

import practice.Practice;

/**
 * 1로 만들기
 * - 정수 X에 사용할 수 있는 연산은 아래 세 가지
 * 1. X가 3으로 나눠 떨어지면 3으로 나눈다.
 * 2. X가 2로 나눠 떨어지면 2로 나눈다.
 * 3. 1을 뺀다.
 *
 * 어떤 정수 N에 위와 같은 연산을 선택해서 1을 만들려고 한다.
 * 연산을 사용하는 횟수의 최소값을 구하여라.
 * d[N] = N을 1로 만드는 최소 연산 횟수
 *
 * 풀이 과정:
 * 핵심은 N을 작게 만드는 것 => 작게 만드는 방법은 이미 문제에 나와있음
 *
 * N이 N/3을 거쳐 1이 된다면 연산횟수는?
 * 1 + d[N/3]
 *
 * N이 N/2를 거쳐 1이 된다면
 * 1 + d[N/2]
 *
 * N이 N-1을 거쳐 1이 된다면
 * 1 + d[N-1]
 *
 * 즉 쉽게 표시하면 아래와 같다 .
 * d[N] = min(d[N/3], d[N/2], d[N-1] + 1;
 *
 * */
public class MakingOne extends Practice {
    int[] d = new int[100];

    @Override
    public void run() {
        printAnswer(goTopDown(4));
    }

    private int goTopDown(int n) {
        if (n == 1) return 0;
        if (d[n] > 0) return d[n];

        d[n] = goTopDown(n - 1) + 1;

        if (n % 2 == 0) {
            int temp = goTopDown(n / 2) + 1;
            if (d[n] > temp) d[n] = temp;
        }

        if (n % 3 == 0) {
            int temp = goTopDown(n / 3) + 1;
            if (d[n] > temp) d[n] = temp;
        }

        return d[n];
    }

    void goBottomUp(int n) {
        d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0 && d[i] > d[i/2] + 1) {
                d[i] = d[i / 2] + 1;
            }

            if (i % 3 == 0 && d[i] > d[i / 3] + 1);
            d[i] = d[i / 3] + 1;
        }
    }
}
