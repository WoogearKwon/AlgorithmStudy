package algorithm.Sort;

import practice.Practice;

import java.util.Arrays;

public class SelectionSort extends Practice {

    // 시간 복잡도: O(n^2) - O(n^2) - O(n^2)
    @Override
    public void run() {
        int[] x = {6,4,8,3,1,9,7};

        selectionSort(x, x.length);

        println("====finished====");
        println(Arrays.toString(x));
    }

     /**
     단순 삽입 정렬 (straight selection sort)
     i가 0부터 증가함에 따라 정렬되지 않은 요소 중 가장 작은 값으로 교환하는 방식
     */
     private void selectionSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i; // 아직 정렬되지 않은 부분에서 가장 작은 요소의 인덱스를 기록
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min); // 정렬되지 않은 부분의 첫 요소와 가장 작은 요소를 교환
        }
    }

    private void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }
}
