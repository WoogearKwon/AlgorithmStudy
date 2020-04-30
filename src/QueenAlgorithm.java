import java.io.PrintStream;

public class QueenAlgorithm {
    static boolean[] flag_a;
    static boolean[] flag_b;
    static boolean[] flag_c;
    static int[] pos;
    static int solutions = 0;
    static int n;

    public static void main(String[] args) {
        PrintStream sys = System.out;

        // 한 변의 길이
        n = 8;

        flag_a = new boolean[n];
        flag_b = new boolean[n * 2 - 1];
        flag_c = new boolean[n * 2 - 1];
        pos = new int[n];

        set(0);


        println("number of solutions = " + solutions);
    }

    static void print() {
        for (int i = 0; i < n; i ++) System.out.print(pos[i] + " ");
//        for (int i = 0; i < pos.length; i ++) {
//            for (int j = 0; j < pos.length; j ++) {
//                print(pos[i] == j ? "■ " : "□ ");
//            }
//            println("");
//        }
        System.out.println();
        System.out.println();

        solutions ++;
    }

    static void set(int i) {
        for (int j = 0; j < n; j++) {
            if (!flag_a[j] && !flag_b[i + j] && !flag_c[i - j + n - 1]) {
                pos[i] = j;

                if (i == n - 1) print();
                else {
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + n - 1] = true;
                    set(i + 1);
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + n - 1] = false; // 확인결과 불가능한 경우, 다시 해당위치 값 = false
                }
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

//    static void print(int txt) {
//        System.out.print(txt);
//    }
}
