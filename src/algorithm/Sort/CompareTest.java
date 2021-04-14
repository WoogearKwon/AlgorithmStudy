package algorithm.Sort;

import practice.Practice;

import java.util.Arrays;
import java.util.Comparator;

public class CompareTest extends Practice {

    // 신체검사 데이터
    static class PhyscData {
        private String name; // 이름
        private int height; // 키
        private double vision; // 시력

        // 생성자
        public PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        // 문자열을 반환합니다.
        public String toString() {
            return name + " " + height + " " + vision;
        }
        //
        public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
        //
        private static class HeightOrderComparator implements Comparator<PhyscData> {
            @Override
            public int compare(PhyscData d1, PhyscData d2) {
                return (d1.height > d2.height) ? 1 : (d1.height < d2.height) ? -1 : 0;
            }
        }
    }

    @Override
    public void run() {
        PhyscData[] x = { // 배열의 요소는 시력순이지 않으면 안됩니다.
                new PhyscData("이나령", 162, 0.3), // 0
                new PhyscData("유지훈", 168, 0.4), // 1
                new PhyscData("김한결", 169, 0.8), // 3
                new PhyscData("홍준기", 171, 1.5), // 5
                new PhyscData("전서현", 173, 0.7), // 2
                new PhyscData("이호연", 174, 1.2), // 4
                new PhyscData("이수민", 175, 2.0), // 6
        };
        int height = 162;

        int idx = Arrays.binarySearch(
                x, // 배열 x에서
                new PhyscData("", height, 0.0), // 키가 vision인 요소를
                PhyscData.HEIGHT_ORDER // VISION_ORDER를 사용하여 검색
        );

        if (idx < 0)
            printAnswer("그 값의 요소가 없습니다.");
        else {
            System.out.println("그 값은 " + "x[" + idx + "]에 있습니다.");
            System.out.println(x[idx]);
        }
    }
}
