import java.util.Arrays;

public class ShellSort {
    static int count = 0;

    // 시간 복잡도: O(n) - O(nlog n^2) - O(nlog n^2)
    public static void main(String[] args) {
//        int[] x = {7, 22, 5, 11, 32, 120, 68, 2};
//        int[] x = {8,1,4,2,7,6,3,5};
//        int[] x = {10,8,1,4,2,7,6,13,5,3,11};
        int[] x = {7,6,104,5,6,8,9,11,1,4,5,53,3,4};
        int n = x.length;

        println("original." + Arrays.toString(x));

//        insert(x, n); // 단순삽입정렬, 시간복잡도 O(n제곱)
//        insert2(x, n); // 셸정렬, 시간복잡도 O(n) ====> 요소 이동 횟수가 훨씬 줄어듦
        insert3(x, n); // h의 값이 서로 배수인 관계로 변하지 않게 함으로써 요소들이 더 충분히 섞여 효율적인 정렬을 하게함

        println(Arrays.toString(x));
        println("count = " + count);
    }

    static void insert3(int[] a, int n) {
        int h;
        for (h = 1; h < n / 9; h = h * 3 + 1);
        for ( ; h > 0; h /= 3) {
            for (int i = h; i < n; i ++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {
                    a[j + h] = a[j];
                    print("h = " + h + ", i = " + i + ", j = " + j + " tmp = " + tmp + "::::  ");
                    println("  sorting1.." + Arrays.toString(a));
                    count ++;
                }
                a[j + h] = tmp;
                print("h = " + h + ", i = " + i + ", j = " + j + " tmp = " + tmp + "::::  ");
                println("  sorting1.." + Arrays.toString(a));
            }
        }
    }

    static void insert2(int[] a, int n) {
        for (int h = n / 2; h > 0; h /= 2) {  // n이 8일때 h = 4, 2, 1 순서로 작아짐
            for (int i = h; i < n; i ++) { // i는 타겟위치
                int j;
                int tmp = a[i];

                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) { //
                    a[j + h] = a[j];
                    print("h = " + h + ", i = " + i + ", j = " + j + " tmp = " + tmp + "::::  ");
                    println("  sorting1.." + Arrays.toString(a));
                    count ++;
                }
                a[j + h] = tmp;
                print("h = " + h + ", i = " + i + ", j = " + j + " tmp = " + tmp + "::::  ");
                println("  sorting2.." + Arrays.toString(a));
            }
        }
    }

    static void insert(int[] a, int n) {
        for (int i = 1; i < n; i ++) {
            int tmp = a[i];
            int j;

            for (j = i; j > 0 && a[j - 1] > tmp; j --) {
                a[j] = a[j - 1];
                count ++;
            }
            a[j] = tmp;
            println("sorting.." + Arrays.toString(a));
        }
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
}
