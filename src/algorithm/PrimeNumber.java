package algorithm;

import practice.Practice;

public class PrimeNumber extends Practice {

    @Override
    public void run() {
//        isPrimeNumber(68);
        getPrimeNumber(10000);
    }

    private void isPrimeNumber(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                printf("%d is NOT a prime number", number);
                return;
            }
        }
        printf("%d is a prime number", number);
    }

    // 에라토스테네스의 채 (Sieve of Eratosthenes)
    // 대량의 숫자에서 소수를 찾는 가장 빠르고 정확한 알고리즘
    private void getPrimeNumber(int totalNumber) {
        int[] sieve = new int[totalNumber + 1];

        for (int i = 2; i <= totalNumber; i++) {
            // 채 안의 숫자를 초기화한다.
            sieve[i] = i;
        }

        for (int i = 2; i <= totalNumber; i++) {
            // 이미 지워진 숫자는 건너뛴다.
            if (sieve[i] == 0) continue;

            // 배수로 시작해서 범위 안의 모든 배수를 삭제한다.
            for (int j = i + i; j <= totalNumber; j += i) {
                sieve[j] = 0;
            }
        }

        // 이제 sieve에 남은 숫자(0이 아닌 숫자)는 모두 소수이다.
        for (int i = 2; i <= totalNumber ; i++) {
            if (sieve[i] != 0) System.out.printf("%d ", sieve[i]);
        }
    }
}