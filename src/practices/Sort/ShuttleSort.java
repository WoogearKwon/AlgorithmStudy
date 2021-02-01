package practices.Sort;

import practices.Practice;

import java.util.Arrays;

public class ShuttleSort extends Practice {

    @Override
    public void run() {
        int[] x = {6,4,8,3,1,9,7};

        insertSort(x, x.length);
        printAnswer(Arrays.toString(x));
    }

    /**
    단순 삽입 정렬 (straight insertion sort)
     i의 값이 증가함에 따라 a[i]의 값을 정렬된 부분 중 맞는 위치에 삽입
    */
    private void insertSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            int j;
            int tmp = a[i];
            for (j = i; j > 0 && a[j - 1] > tmp; j --) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }
}
