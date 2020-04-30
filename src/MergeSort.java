import java.util.Arrays;

public class MergeSort {

    static int[] buff;

    public static void main(String[] args) {
//        int[] x = {7,22,5,11,32,120,68,70};
        int[] x = {3,2,1};
        int n = x.length;

        buff = new int[n];

        println("====original====");
        println(Arrays.toString(x));

        mergeSort(x, 0, n - 1);
        buff = null;

        println("====finished====");
        println(Arrays.toString(x));
    }


    // 재귀 호출
    static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            mergeSort(a, left, center);
            mergeSort(a, center + 1, right);

            for (i = left; i <= center; i ++) {
                buff[p++] = a[i];
            }

            while (i <= right && j < p) {
                a[k++] = buff[j] <= a[i] ? buff[j++] : a[i++];
                println("sorting....." + Arrays.toString(a));
            }

            while (j < p) {
                a[k++] = buff[j++];
                println("sorting....." + Arrays.toString(a));
            }
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

    static void print(int txt) {
        System.out.println(txt);
    }
}
