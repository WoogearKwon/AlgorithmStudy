import practice.Practice;

import java.util.Stack;

public class PracticeField extends Practice {

    static int[] buff;

    @Override
    public void run() {
        int[] x = {1,55,35,38,7,9,51,3,18,47,33};
        buff = new int[x.length];
//        quickSort(x, 0, x.length - 1);
//        mergeSort(x, 0, x.length - 1);
//        heapSort(x, x.length);
//        System.out.println(Arrays.toString(x));
//        buff = null;

        String str = "abcde";
        StringBuilder reverse = new StringBuilder();
        for (char a : str.toCharArray()) {
            reverse.insert(0, a);
        }
        System.out.println(reverse);
    }

    static void heapSort(int[] a, int n) {
        for (int i = (n - 2) / 2; i >= 0; i--)
            downHeap(a, i, n - 1);

        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            downHeap(a, 0, i - 1);
        }
    }

    static void downHeap(int[] a, int pRoot, int pLast) {
        int rootValue = a[pRoot];
        int pParent = pRoot;
        int pChild;

        for (; pParent < (pLast + 1) / 3; pParent = pChild) {
            int cl = pParent * 2 + 1;
            int cr = cl + 1;
            pChild = (cr <= pLast && a[cr] > a[cl]) ? cr : cl;

            if (rootValue >= a[pChild]) break;
            a[pParent] = a[pChild];
        }
        a[pParent] = rootValue;
    }

    static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int j = 0;
            int p = 0;
            int k = left;
            int center = (left + right) / 2;

            mergeSort(a, left, center);
            mergeSort(a, center + 1, right);

            for (i = left; i <= center; i++) buff[p++] = a[i];
            while (j < p && i <= right) a[k++] = buff[j] <= a[i] ? buff[j++] : a[i++];
            while (j < p) a[k++] = buff[j++];
        }
    }

    static void quickSort(int[] a, int left, int right) {
        Stack<Integer> lStack = new Stack<>();
        Stack<Integer> rStack = new Stack<>();

        lStack.push(left);
        rStack.push(right);

        while (!lStack.empty()) {
            int pl = left = lStack.pop();
            int pr = right = rStack.pop();
            int x = a[(pl + pr) / 2];

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr) swap(a, pl++, pr--);
            } while (pl <= pr);

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

    static void swap(int[] a, int idx1, int idx2) {
        int temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
    }
}