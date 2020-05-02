import java.util.Arrays;

public class HeapSort {

    // 시간 복잡도: O(nlog n) - O(nlog n) - O(nlog n)
    // 힙 정렬은 선택정렬을 응용한 알고리즘
    // 단순선택정렬은 전체 정렬에 걸리는 시간 복잡도 값이  O(n^2)이지만,
    // 힙정렬은 힙으로 만드는 작업을 요소의 개수만큼 반복하므로 시간복잡도의 값이 크게 줄어든다.
    public static void main(String[] args) {
        int[] x = {22,5,11,32,120,68,70};

        heapSort(x, x.length);
        println("====finished====");
        println(Arrays.toString(x));
    }

    static void heapSort(int[] a, int n) {
        // a[i] ~ a[n-1]를 힙으로 만들기
        for (int i = (n - 1) / 2; i >= 0; i --) {
            downHeap(a, i, n - 1);
        }

        for (int i = n - 1; i > 0; i --) {
            swap(a, 0, i); // 가장 큰 요소와 아직 정렬되지 않은 부분의 마지막 요소를 교환
            println("sorting..." + Arrays.toString(a));
            downHeap(a, 0, i - 1); // a[0] ~ a[i-1]을 힙으로 만들기
        }
    }

    static void downHeap(int[] a, int left, int right) {
        int temp = a[left]; // 루트
        int child;          // 큰 값을 가진 노드
        int parent;         // 부모

        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1; // left child
            int cr = cl + 1;         // right child
            child = (cr <= right && a[cr] > a[cl]) ? cr : cl; // 큰 값을 가진 노드를 자식에 대입

            if (child >= a[child]) break;

            a[parent] = a[child];
            println("sorting..." + Arrays.toString(a));
        }
        a[parent] = temp;
        println("sorting..." + Arrays.toString(a));
   }

    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
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
