package practices.Sort;

import practices.Practice;

import java.util.Arrays;

public class HeapSort extends Practice {

    /**
     시간 복잡도: O(nlog n) - O(nlog n) - O(nlog n)
     힙 정렬은 선택정렬을 응용한 알고리즘이지만 더욱 효율적이다. (퀵정렬, 병합정렬 보다 효율적)
     단순선택정렬은 전체 정렬에 걸리는 시간 복잡도 값이  O(n^2)이지만,
     힙정렬은 힙으로 만드는 작업을 요소의 개수만큼 반복하므로 시간복잡도의 값이 크게 줄어든다.
     */
    @Override
    public void run() {
        int[] x = {1,55,33,38,7,9,51,3,18,47,33};
//        int[] x = {2,1,8,65,4,8,1,3,4,8,2,1,6};

        println("====original====");
        println(Arrays.toString(x));

        heapSort(x, x.length);
        println("====finished====");
        println(Arrays.toString(x));
    }

    /*
    * find index:
    * parent = a[(i - 1) / 2]
    * left child = a[i * 2] + 1]
    * right child = left child + 1 .. OR .. a[i * 2] + 2
    * */

     /**
     힙 정렬 순서
     1. 배열을 힙 상태로 만든다.
     2. 루트(최대값)를 마지막 요소와 교환한다.
     3. 새로운 루트값을 알맞은 위치로 이동시킨다. (다른 요소들은 힙상태를 유지)
     4. 정렬이 완료될 때까지 과정(2~3)을 반복한다.
     */
     private void heapSort(int[] a, int n) {
        // 1. 배열을 힙 상태로 만들기
        for (int i = (n - 2) / 2; i >= 0; i --) { // i = 마지막 요소의 부모 요소부터 시작, 1씩 감소
            downHeap(a, i, n - 1);
        }

        // 2. 정렬하기 (최대값을 맨부터 넣는 순서로 정렬)
        println("\n============= Heap Sorting ==========");
        for (int i = n - 1; i > 0; i --) { // i = 정렬되지 않은 부분의 마지막 요소
            swap(a, 0, i); // 루트(최대값)와 아직 정렬되지 않은 부분의 마지막 요소를 교환   (마지막 요소는 1씩 감소)
            println("[A]sorting...a[" + 0 + "] <-> a[" + i + "].. " + Arrays.toString(a));
            downHeap(a, 0, i - 1); // a[0] ~ a[i-1]을 힙으로 만들기
        }
    }

    private void downHeap(int[] a, int pRoot, int pLast) {
        int rootValue = a[pRoot]; // 루트(부모)
        int pChild;               // 큰 값을 가진 노드
        int pParent;              // 부모

        for (pParent = pRoot; pParent < (pLast + 1) / 2; pParent = pChild) {  // (pLast + 1) / 2 ===> 마지막 요소의 부모 노드까지만 반복함
            int cl = pParent * 2 + 1; // left child
            int cr = cl + 1;          // right child
            pChild = (cr <= pLast && a[cr] > a[cl]) ? cr : cl; // 2개의 차일드 중 큰 값을 가진 노드 (cr 혹은 cl)

            println("\nparentPosition = " + pParent + ", childPosition = " + pChild + ", parentValue = " + rootValue + ", a[child] = " + a[pChild]);
            if (rootValue >= a[pChild]) break; // 정렬하고자 하는 값보다 차일드 값이 크다면 더이상 정렬할 필요 없음 (루프 중지)

            a[pParent] = a[pChild]; // 큰 값이 위로 올라옴
            println("[B]sorting...a[" + pParent + "] <-> a[" + pChild + "].. " + Arrays.toString(a));
        }

        a[pParent] = rootValue; // 마지막으로 루트 위치에 있던 값(작은 값)을 알맞은 위치(아래)로 이동 완료
        println("[C]sorting...a[" + pParent + "] <-> a[" + pRoot + "].. "+ Arrays.toString(a));
   }

    private void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }
}
