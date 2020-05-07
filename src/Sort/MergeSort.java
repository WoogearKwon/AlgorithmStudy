import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class MergeSort {
    static int[] buff; // 작업용 배열

    // 시간 복잡도: O(nlog n) - O(nlog n) - O(nlog n)
    public static void main(String[] args) {
//        executeMergeSort();
//        sortCalendar();
        comparePhysicsData();
    }

    static void comparePhysicsData() {
        PhysicsData[] x = {
                new PhysicsData("David", 182, 0.3),
                new PhysicsData("Eric", 194, 0.9),
                new PhysicsData("John", 178, 1.0),
                new PhysicsData("Hannah", 165, 1.4),
                new PhysicsData("Michael", 204, 2.0),
                new PhysicsData("Jennifer", 175, 1.0),
                new PhysicsData("Woogear", 180, 1.4),
        };

        Arrays.sort(x, PhysicsData.HEIGHT_ORDER);

        println("Physics Test Results");
        println("name    height  vision");
        println("-------------");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%-9s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
        }
    }

    /**
     자연 정렬
     GregorianCalendar 클래스는 Comparable 인터페이스와 compareTo() 메서드를 구현함
     1~12월을 0~11으로 표현 => 때문에 .get(MONTH)에 의해 얻는 값은 0~11이므로 출력할 때는 1을 더해야 한다.
     */
    static void sortCalendar() {
        GregorianCalendar[] x = {
                new GregorianCalendar(2017, Calendar.NOVEMBER, 1),
                new GregorianCalendar(1963, Calendar.OCTOBER, 18),
                new GregorianCalendar(1985, Calendar.APRIL, 5),
                new GregorianCalendar(1985, Calendar.APRIL, 2),
        };

        Arrays.sort(x);

        for (int i = 0; i < x.length; i ++) {
            System.out.printf("%04d년 %02d월 %02d일\n",
                    x[i].get(Calendar.YEAR),
                    x[i].get(Calendar.MONTH) + 1, // 1을 더함
                    x[i].get(Calendar.DATE)
            );
        }
    }

    static void executeMergeSort() {
//        int[] x = {7,22,5,11,32,120,68,70};
        int[] x = {3,2,1};
        int n = x.length;

        buff = new int[n];

        println("====original====");
        println(Arrays.toString(x));

        mergeSort(x, 0, n - 1);
        buff = null;
    }

    // 재귀적 병합 정렬
    static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0; // buff에 복사한 요소 개수
            int j = 0; // buff에 접근하는 인덱스
            int k = left; // 배열 a에 값을 넣을 때 사용할 인덱스

            mergeSort(a, left, center); // 배열의 앞부분을 병합 정렬 (요소가 2개인 배열에는 동작하지 않음)
            mergeSort(a, center + 1, right); // 배열의 뒷부분을 병합정렬

            // ======== 여기서부터 앞부분과 뒷부분을 병합 =======

            // 배열의 앞부분을 buff에 복사
            // for문이 끝날 때 p의 값은 복사한 요소의 개수, 즉 center - left + 1이 된다.
            for (i = left; i <= center; i ++) {
                buff[p++] = a[i];
            }

            // 배열의 뒷부분과, buff로 복사한 배열의 앞부분 p개를 병합한 결과를 배열 a에 저장한다.
            while (i <= right && j < p) {
                a[k++] = buff[j] <= a[i] ? buff[j++] : a[i++];
                println("sorting....." + Arrays.toString(a));
            }

            // 배열 buff에 남아있는 요소를 배열 a에 복사
            while (j < p) {
                a[k++] = buff[j++];
                println("sorting....." + Arrays.toString(a));
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

    static void print(int txt) {
        System.out.println(txt);
    }

    static class PhysicsData {
        String name;
        int height;
        double vision;

        public PhysicsData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        static final Comparator<PhysicsData> HEIGHT_ORDER = new HeightOrderComparator();

        private static class HeightOrderComparator implements Comparator<PhysicsData> {
            @Override
            public int compare(PhysicsData o1, PhysicsData o2) {
                return Integer.compare(o1.height, o2.height); // 아래와 동일
                // return o1.height > o2.height ? 1 : o1.height < o2.height ? -1 : 0;
            }
        }
    }
}
