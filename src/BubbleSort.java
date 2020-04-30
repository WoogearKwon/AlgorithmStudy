import java.util.Arrays;

public class BubbleSort {

    static int n;

    public static void main(String[] args) {
//        int x[] = {7, 22, 5, 11, 32, 120, 68, 70};
//        int x[] = {5, 4, 3, 2, 1};
//        int x[] = {1, 3, 9, 4, 7, 8, 6}; // bubbleSort3 테스트용
        int x[] = {9, 1, 3, 4, 6, 7, 8}; // bubbleSort4 테스트용
        n = x.length;
        println("original array = " + Arrays.toString(x) + "\n");

//        bubbleSort1();
//        bubbleSort2(x);
        bubbleSort3(x);

        println("\n-----FINISH SORITNG----");
        println("sorted array = " + Arrays.toString(x));
    }

    // 개선4: 네번째 x[] 의 경우 bubbleSort3()을 사용해도 빠른 정렬이 안된다.
    // 홀수번째 패스는 가장 작은 요소를 맨 앞으로 옮기고,
    // 짝수번째 패스는 가장 큰 요소를 맨 뒤로 옮기는 방식을 사용
    static void bubbleSort4(int[] x) {
        int k = 0;

        while (k < n - 1) {
            int last = n - 1; // 마지막으로 교환한 오른쪽 요소의 인덱스

            for (int j = n - 1; j > k; j --) {
                if (x[j - 1] > x[j]) {
                    swap(x, j - 1, j);
                    last = j;
                }
            }
            // 한번의 정렬 계산이 끝나면 last를 K에 대입하여 다음에 수행할 계산 범위를 제한
            k = last;
        }
    }

    // 개선3: 앞쪽에 정렬이 끝난 부분이 있다면 순서대로 진행하지 않고 건너뛴다.
    static void bubbleSort3(int[] x) {
        int k = 0;
    }

    // 개선2: 정렬이 종료되면 루프를 종료시키도록 개선하여 불필요한 연산 감소
    static void bubbleSort2(int[] x) {
        for (int i = 0; i < n - 1; i ++) {
            int exchange = 0;
            println("----------i(" + i + ")----------");
            for (int j = n - 1; j > i; j --) {
                if (x[j - 1] > x[j]) {
                    swap(x, j, j - 1);
                    exchange ++;
                }
            }
            if (exchange == 0) { // 교환이 이루어지지 않았음 => 정렬이 완료됨 => 루프 종료
                println("\n******* no more sort needed ******");
                break;
            }
        }
    }

    static void bubbleSort1(int[] x) {
        for (int i = 0; i < n - 1; i ++) {
            println("----------i(" + i + ")----------");
            for (int j = n - 1; j > i; j --) {
                if (x[j - 1] > x[j]) swap(x, j, j - 1);
            }
        }
    }

    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;

        println("sorting array = " + Arrays.toString(a));
    }

    static void println(String txt) {
        System.out.println(txt);
    }

    static void println(int txt) {
        System.out.println(txt);
    }

    static void println(boolean txt) {
        System.out.println(txt);
    }

    static void print(String txt) {
        System.out.print(txt);
    }

//    static void print(int txt) {
//        System.out.print(txt);
//    }
}
