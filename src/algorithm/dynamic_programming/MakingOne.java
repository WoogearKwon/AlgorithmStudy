package algorithm.dynamic_programming;

import practice.Practice;

public class MakingOne extends Practice {
    int[] d = new int[100];

    @Override
    public void run() {
        printAnswer(goTopDown(6));
    }

    /*
      Top-down 방식
      */
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
