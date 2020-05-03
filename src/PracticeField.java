import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;

public class PracticeField {

    public static void main(String[] args) {
//        int[] x = {7,6,104,5,6,3,8,9,11,1,4,3,2,3,5,53,3,4};
        int[] x = {22,5,11,32,120,68,70};
        int n = x.length;


        println("====finished====");
        println(Arrays.toString(x));
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