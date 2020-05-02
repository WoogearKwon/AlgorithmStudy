import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;

public class QuickSort {

    static int count = 0;

    // 시간 복잡도: O(nlog n) - O(nlog n) - O(n^2)
    public static void main(String[] args) {
        int[] x = {1,8,7,4,5,2,6,3,9};
//        int[] x = {5,8,4,2,6,10,8,3,9,7};
//        int[] x = {7,5,2,3,4,6,8,9,10};

//        partition(x, x.length);
//        quickSort1(x, 0, x.length - 1);
        quickSort2(x, 0, x.length - 1);

        println("finished");
        println(Arrays.toString(x));
    }

    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    // 스택 사용 (비재귀적 퀵정렬)
    // LIFO(후입선출)이기 때문에 아래 quickSort1()과 순서가 다름
    // * 요소의 개수가 많은 그룹을 먼저 스택에 푸시하면 더 적은 스택용량을 사용할 수 있음
    static void quickSort2(int[] a, int left, int right) {
        IntStack lStack = new IntStack(right - left + 1);
        IntStack rStack = new IntStack(right - left + 1);

        lStack.push(left);
        rStack.push(right);

        while (!lStack.empty()) {
            int pl = left = lStack.pop();
            int pr = right = rStack.pop();
            int x = a[(left + right) / 2]; // pivot
            count ++;

            do {
                while (a[pl] < x) pl ++;
                while (a[pr] > x) pr --;
                if (pl <= pr) swap(a, pl ++, pr --);
            } while (pl <= pr); // pl, pr의 위치가 교차되기 전까지 반복

            println("sorting(" + count + ").....pivot=" + x + " " + Arrays.toString(a));

            if (left < pr) {
                lStack.push(left);
                rStack.push(pr);
            }

            if (pl < right) {
                lStack.push(pl);
                rStack.push(right);
            }
        }
    }

    // 재귀호출 사용
    static void quickSort1(int[] a, int left, int right) {
        int pl = left; // left cursor
        int pr = right; // right cursor
        int x = a[(pl + pr) / 2]; // pivot
        count ++;

        do {
            while (a[pl] < x) pl ++;
            while (a[pr] > x) pr --;
            if (pl <= pr) swap(a, pl++, pr--);
        } while (pl <= pr); // pl, pr의 위치가 교차되기 전까지 반복
        // 위 까지는 partition()과 동일

        println("sorting(" + count + ").....pivot=" + x + " " + Arrays.toString(a));

        // 재귀 호출로 추가 분할x
        if (left < pr) quickSort1(a, left, pr);
        if (pl < right) quickSort1(a, pl, right);
    }

    static void partition(int[] a, int n) {
        int pl = 0;
        int pr = n - 1;
        int x = a[n/2];

        do {
            while (a[pl] < x) pl ++;
            while (a[pr] > x) pr --;
            if (pl <= pr) swap(a, pl++, pr--);
        } while (pl <= pr);
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

    static void print(int txt) {
        System.out.println(txt);
    }
}
