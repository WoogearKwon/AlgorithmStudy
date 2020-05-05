public class StringSearch {

    /**
     * 문자열 검색 알고리즘
     * */
    public static void main(String[] args) {
        String text = "ABCFI이지스DEF";  // 원본 텍스트
        String pattern = "이지스";     // 검색할 텍스트

        int idx = bfMatch(text, pattern);

//        if (idx == -1) {
//            println("텍스트에 패턴이 없음");
//        } else {
//            println((idx + 1) + "번째 문자부터 일치");
//        }

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


    static void println(String txt) {
        System.out.println(txt);
    }

    static void println(int txt) {
        System.out.println(txt);
    }

    static void println(boolean txt) {
        System.out.println(txt);
    }
}
