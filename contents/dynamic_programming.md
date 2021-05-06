# 동적 계획법 (Dyanmic Programming, DP)

- 큰 문제를 작은 문제로 나눠서 푸는 알고리즘
- 1940년 Richard Bellman이 처음 사용함 (멋있어서 사용했다고 함)
- 중복이 생길 수 있다. (반면 분할정복은 중복이 생기지 않음)
- 아래 두 가지 속성을 만족해야 다이나믹 프로그래밍으로 문제를 풀 수 있다. 
    - Overlapping Subproblem: 겹치는 부분(작은) 문제
    - Optimal Substructure: 최적 부분 구조 (작은 문제의 정답을 통해 답을 구할 수 있음 )
- 다이나믹 프로그래밍에서 각 문제는 한 번만 풀어야 한다.
- Optimal Substructure를 만족하기 때문에, 같은 문제를 구할 때마다 정답이 같다.
- 따라서, 정답을 한 번 구했으면 정답을 어딘가에 메모해놓는다.
- 배열에 데이터를 메모할 수 있다. (Memorization) 
    
### 예) 피보나치 수열
Fn = (Fn-1) + (Fn-2)

큰문제: Fn

작은문제: (Fn-1) + (Fn-2)

=> 큰 문제를 풀기 위해 작은 문제를 반복해서 푼다.

#### Top-down 방식
- 큰 문제부터 작게 쪼개서 다시 합쳐가면서 푸는 방식
- 재귀 호출을 사용

#### Bottom-up 방식
- 작은 문제들을 모아 큰 문제를 만들고 계속 반복하면서 쌓아올라가는 방식
- 반복하다보면 큰 문제를 풀 수 있음
- 반복문을 사용

재귀호출을 이용한 피보나치 수열 알고리즘 코드(아래)
```java
int[] memo = new int[100];
int fiboFast(int n) {
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
```

반복문을 이용한 피보나치 수열 알고리즘 코드(아래)
```java
void runLoopFibonacci(int n) {
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
```