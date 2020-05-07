import LinkedList.ArrLinkedList;
import com.sun.org.apache.xml.internal.utils.IntStack;

import java.util.Arrays;
import java.util.Comparator;

public class PracticeField {

    static int[] buff;

    public static void main(String[] args) {
        int[] x = {1,55,33,38,7,9,51,3,18,47,33};

        ArrLinkedList<String> list = new ArrLinkedList<String>(10);
        list.addFirst("haha");
        list.addLast("hoho");
        list.addLast("keke");
        list.addLast("AAAA");
        list.addLast("BBBBB");

        list.removeFirst();
        list.addLast("LISJEF");

        list.remove(4);
        list.addLast("@948");
        list.dump();
    }

    public static final Comparator<String> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<String> {
        public int compare(String d1, String d2) {
            return d1.compareTo(d2);
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