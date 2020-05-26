package etc;

public class StringSearch {

    /**
     * 문자열 검색 알고리즘
     * */
    public static void main(String[] args) {
        String text = "he is from korea..";  // 원본 텍스트
        String pattern = "korea";     // 검색할 텍스트

//        int idx = bfMatch(text, pattern);
//        int idx = kmpMatch(text, pattern);
        int idx = bmMatch(text, pattern);

        if (idx == -1)
            System.out.println("텍스트에 패턴이 없습니다.");
        else {
            printResult(idx, text, pattern);
        }
    }

    static void printResult(int idx, String text, String pattern) {
        int len = 0;
        for (int i = 0; i < idx; i++)
            len += text.substring(i, i + 1).getBytes().length;
        len += pattern.length();

        System.out.println((idx + 1) + "번째 문자와 일치합니다.");
        System.out.println("text : " + text);
        System.out.printf(String.format("patt : %%%ds\n", len), pattern);
    }

    /**
     * 브루트-포스법
     * */
    static int bfMatch(String txt, String pat) {
        int pt = 0; // txt 커서
        int pp = 0; // pattern 커서

        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else {
                pt = pt - pp + 1; // 초기화 & 다음 인덱스
                pp = 0;
            }
        }

        if (pp == pat.length()) return  pt - pp; // 검색 성공
        return -1;                               // 검색 실패
    }

    /**
     * 고안자 Knuth, Morris, Pratt의 앞글자를 따서 지은 이름
     * 브루트-포스에 비해 효율적
     * 이전에 검사했던 위치의 결과를 버리지 않고 활용함
     * 그러나 복잡하고 성능이 Boyer-Moore법과 비교해 같거나 좋지 않음, 실제 거의 사용하지 않음
     * */
    static int kmpMatch(String txt, String pat) {
        int pt = 1;                             // txt 커서
        int pp = 0;                             // patter 커서
        int[] skip = new int[pat.length() + 1]; // 건너뛰기 표

        // 건너뛰기 표 만들기
        skip[pt] = 0;
        while (pt != pat.length()) {
            if (pat.charAt(pt) == pat.charAt(pp)) skip[++pt] = ++pp;
            else if (pp == 0) skip[++pt] = pp;
            else pp = skip[pp];
        }

        // 검색
        pt = pp = 0;
        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else if (pp == 0) {
                pt++;
            } else {
                pp = skip[pp];
            }
        }

        if (pp == pat.length()) return pt - pp;
        return -1; // 검색 실패
    }

    /**
     * R.S.Boyer와 J S.Moore가 만듦
     * 패턴의 마지막 문자부터 앞쪽으로 검사를 진행하면서
     * 일치하지 않는 문자가 있으면 미리 준비한 표에 따라 패턴을 옮길 크기를 정함
     * 패턴에 존재할 수 있는 모든 문자의 옮길 크기를 계산 => 건너뛰기 표의 요소 개수 = Character.MAX_VALUE + 1
     * */
    static int bmMatch(String txt, String pat) {
        int pt;                     // txt 커서
        int pp;                     // pat 커서
        int txtLen = txt.length();  // txt의 문자 개수
        int n = pat.length();       // pat의 문자 개수
        int[] skip = new int[Character.MAX_VALUE + 1]; //  skip table (건너뛰기 표)

        // 건너뛰기 테이블(skip table) 만들기
        for (pt = 0; pt <= Character.MAX_VALUE; pt++) skip[pt] = n; // 우선 skip[]의 모든 요소들의 값을 n으로 한다.
        for (pt = 0; pt < n - 1; pt++) skip[pat.charAt(pt)] = n - pt - 1; // pat의 문자들에 대한 skip[]의 값을 정해준다. (n-1 위치의 문자는 무시)

        //검색
        while (pt < txtLen) {
            pp = n - 1; // 초기화

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0) return pt; // 검색 성공
                pp--;
                pt--;
            }
            // 건너뛸 칸의 개수 정의
            pt += (skip[txt.charAt(pt)] > n - pp) ? skip[txt.charAt(pt)] : n - pp;
        }
        return -1; // while 문이 종료되면 검색 실패
    }
}
