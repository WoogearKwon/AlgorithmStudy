# 최대공약수 (Greatest Common Divisor, GCD)

- 주어진 두 개 이상의 숫자들이 공통으로 가지는 약수 중 가장 큰 수를 구하기
- [소스코드](/src/algorithm/GreatestCommonDivisor.java)


### 일반적인 방법
- 2~n 까지의 반복문을 돌면서 주어진 두 숫자가 모두 0으로 나누어 떨어지는 가장 큰 수를 찾는 방법
- 시간복잡도: O(N)
```java
void gcd(int a, int b) {
    int answer = 1;
    for (int i = 2; i <= Math.min(a, b); i++) {
        if (a % i == 0 && b % i == 0) {
            answer = i;
        }
    }

    printAnswer(answer);
}
```

### 유클리드 호제법
- a % b = r 이라고 했을 때, gcd(a,b) = gcd(b,r) 이 성립한다.
- 위 과정을 반복, r = 0 일때 b 가 바로 최대공약수
- 아래는 while 문으로 구현한 방식
````java
void printEcliedeanLoop(int a, int b) {
    while (b != 0) {
        int r = a % b;
        a = b;
        b = r;
    }

    printAnswer(a);
}
````