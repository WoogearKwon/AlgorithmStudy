package data_structure.etc;

/**
집합을 구현한 클래스
* */
public class IntSet {
    int max;    // 집합의 최대 크기
    int num;    // 집합의 요소 개수
    int[] set;  //집합 본체

    public IntSet(int capacity) {
        num = 0;
        max = capacity;
        try {
            set = new int[max];
        } catch (OutOfMemoryError e) {
            max = 0;
        }
    }

    /** 집합의 최대 개수  */
    public int capacity() {
        return max;
    }

    /** 집합의 요소 개수  */
    public int size() {
        return num;
    }
    /** 집합에서 n을 검색하고 index 반환 */
    public int indexOf(int n) {
        for (int i = 0; i < num; i++) {
            if (set[i] == n) return i;
        }
        return -1;
    }

    /** 집합에 n이 있는지 확인 */
    public boolean contains(int n) {
        return indexOf(n) != -1; // true or false
    }

    /** 집합에 n 추가 */
    public boolean add(int n) {
        if (num >= max || contains(n)) { // 가득 찼거나 이미 n이 존재
            return false;
        } else {
            set[num++] = n;              // 가장 마지막 자리에 추가
            return true;
        }
    }

    /** 집합에서 n 삭제 */
    public boolean remove(int n) {
        int idx;                                    //n이 저장된 요소의 인덱스

        if (num <= 0 || (idx = indexOf(n)) == -1) { // 비어있거나 n이 존재하지 않음
            return false;
        } else {
            set[idx] = set[--num];                  // 마지막 요소를 삭제한 곳으로 이동 (집합에서 순서는 중요하지 않으므로)
            return true;
        }
    }

    /** 집합 s에 복사 */
    public void copyTo(IntSet s) {
        int n =(s.max < num) ? s.max : num; // 복사할 요소의 개수
        for (int i = 0; i < n; i++) s.set[i] = set[i];
        num = n;
    }

    /** 집합 s를 복사 */
    public void copyFrom(IntSet s) {
        int n = (max < s.num) ? max : s.num; // 복사할 요소의 개수
        for (int i = 0; i < n; i++) set[i] = s.set[i];
        num = n;
    }

    /** 집합 s와 같은지 확인 */
    public boolean equalTo(IntSet s) {
        if (num != s.num) return false;

        for (int i = 0; i < num; i++) {
            int j = 0;
            for ( ; j < s.num; j++) {
                if (set[i] == s.set[j]) break; // 요소가 일치하면 for문 종료
            }

            if (j == s.num) return false;      // 안쪽 for문이 끝까지 실행되었다면 요소가 일치하지 않음
        }                                      // 바깥쪽 for문이 끝까지 실행되었다면 두 집합은 같음

        return true;
    }

    /** 집합 s1과 s2의 합집합을 복사 */
    public void unionOf(IntSet s1, IntSet s2) {
        copyFrom(s1);
        for (int i = 0; i < s2.num; i++) add(s2.set[i]);
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public boolean isFull() {
        return num == max;
    }

    public void clear() {
//        int n = num;
//        for (int i = 0; i < n; i++) {
//            remove(set[i]);
//        }
        num = 0;
    }

    /** { a b c } 형식의 문자열로 출력 */
    public String toString() {
        StringBuffer temp = new StringBuffer("{ ");
        for (int i = 0; i < num; i++) temp.append(set[i] + ", ");
        temp.append("}");
        return temp.toString();
    }
}
