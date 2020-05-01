import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;

public class TestField {

    public static void main(String[] args) {
        int[] x = {7,6,104,5,6,3,8,9,11,1,4,3,2,3,5,53,3,4};
        int n = x.length;

        println("====finished====");
        println(Arrays.toString(x));
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