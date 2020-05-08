import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;

public class PracticeField {

    static int[] buff;

    public static void main(String[] args) {
        int[] x = {1,55,33,38,7,9,51,3,18,47,33};
    }

    static void swap(int[] a, int idx1, int idx2) {
        int temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
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