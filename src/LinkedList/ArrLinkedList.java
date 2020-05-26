package LinkedList;

import java.util.Comparator;

/** 배열 커서로 만든 연결리스트 */
public class ArrLinkedList<E> {

    class Node<E> {
        private E data;     // 데이터
        private int next;   // 리스트의 뒤쪽 데이터
        private int dnext;  // free 리스트의 뒤쪽 데이터

        // data와 next를 설정
        void set(E data, int next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E>[] n;    // 리스트 본체
    private int size;       // 리스트의 용량 (가장 큰 데이터 수)
    private int max;        // 사용 중인 꼬리 record
    private int head;       // 머리 노드
    private int tail;       // 꼬리 노드
    private int current;    // 선택 노드
    private int dHead;      // free 리스트의 머리 노드
    private static final int NULL = -1; // 다음 노드 없음 / 리스트가 가득 참

    // constructor

    public ArrLinkedList(int capacity) {
        head = current = max = dHead = NULL; // 초기화
        try {
            n = new Node[capacity];
            for (int i = 0; i < capacity; i++)
                n[i] = new Node<E>();
            size = capacity;
        } catch (OutOfMemoryError e) { // 배열 생성에 실패
            size = 0;
        }
    }

    // 다음에 삽입하는 record의 인덱스를 구함
    private int getInsertIndex() {
        if (dHead == NULL) {              // 삭제할 record가 없음
            if (max < size - 1)
                return ++max;             // 새 record를 사용
            else
                return NULL;              // 용량 넘침
        } else {
            int rec = dHead;              // free 리스트에서
            dHead = n[dHead].dnext;       // 머리 rec을 꺼냄
            return rec;
        }
    }

    // record idx를 free 리스트에 등록
    private void deleteIndex(int idx) {
        if (dHead == NULL) {      // free 리스트가 비어있음
            dHead = idx;          // idx를 free 리스트의
            n[idx].dnext = NULL;  // 머리에 등록
        } else {
            int rec = dHead;      // idx를 free 리스트의
            dHead = idx;          // 머리에 삽입
            n[dHead].dnext = rec;
        }
    }

    // 노드를 검색
    public E search(E obj, Comparator<? super E> c) {
        int ptr = head;             // 현재 스캔 중인 노드

        while (ptr != NULL) {
            if (c.compare(obj, n[ptr].data) == 0) {
                current = ptr;
                return n[ptr].data; // 검색 성공
            }
            ptr = n[ptr].next;      // 다음 노드에 주목
        }
        return null;                // 검색 실패
    }

    // 머리에 노드를 삽입
    public void addFirst(E obj) {
        boolean empty = (tail == NULL);
        int ptr = head;             // 삽입 전의 머리 노드
        int rec = getInsertIndex();
        if (rec != NULL) {
            head = current = rec;   // 인덱스 rec인 record에 삽입
            n[head].set(obj, ptr);
            if (empty) tail = current;
        }
    }

    // 꼬리에 노드를 삽입
    public void addLast(E obj) {
        if (head == NULL)       // 리스트가 비어있으면
            addFirst(obj);      // 머리에 삽입
        else {
            int rec = getInsertIndex();
            System.out.println(rec);
            if (rec != NULL) {
                n[tail].next = current = rec;
                n[rec].set(obj, NULL);
                tail = rec;
            }
        }
    }

    // 머리노드를 삭제
    public void removeFirst() {
        if (head != NULL) {         // 리스트가 비어 있지 않아야 수행
            int ptr = n[head].next;
            deleteIndex(head);
            head = current = ptr;
            if (head == NULL)
                tail = NULL;
        }
    }

    // 꼬리노드를 삭제
    public void removeLast() {
        if (head != NULL) {
            if (n[head].next == NULL)   // 노드가 하나만 있으면
                removeFirst();          // 머리노드를 삭제
            else {
                int ptr = head;         // 스캔중인 노드
                int pre = head;         // 스캔중인 노드의 앞쪽노드

                while (n[ptr].next != NULL) {
                    pre = ptr;
                    ptr = n[ptr].next;
                }
                n[pre].next = NULL;     // pre는 삭제 뒤의 꼬리노드
                deleteIndex(ptr);
                tail = current = pre;
            }
        }
    }

    // record p를 삭제
    public void remove(int p) {
        if (head != NULL) {
            if (p == head)          // p가 머리노드면
                removeFirst();      // 머리노드를 삭제
            else if (p == tail)     // p가 꼬리노드면
                removeLast();       // 꼬리노드를 삭제
            else {
                int ptr = head;

                while (n[ptr].next != p) {
                    ptr = n[ptr].next;
                    if (ptr == NULL)
                        return;      // p가 리스트에 없습니다.
                }
                n[ptr].next = n[p].next;
                deleteIndex(p);
                current = ptr;
            }
        }
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        remove(current);
    }

    // 모든 노드를 삭제
    public void clear() {
        while (head != NULL)    // 비게 될 때까지
            removeFirst();      // 머리노드를 삭제
        current = NULL;
    }

    // 선택 노드를 하나 뒤쪽으로 진행
    public boolean next() {
        if (current == NULL || n[current].next == NULL)
            return false; // 나아갈 수 없음
        current = n[current].next;
        return true;
    }

    // 선택 노드를 출력
    public void printCurrentNode() {
        if (current == NULL)
            System.out.println("선택 노드가 없습니다.");
        else
            System.out.println(n[current].data.toString());
    }

    // 모든 노드를 출력
    public void dump() {
        int ptr = head;

        while (ptr != NULL) {
            System.out.println(n[ptr].data.toString());
            ptr = n[ptr].next;
        }
    }

    // comparator c에 의해 서로 같다고 보는 노드를 모두 삭제
    public void purge(Comparator<? super E> c) {
        int ptr = head;

        while (ptr != NULL) {
            int count = 0;
            int ptr2 = ptr;
            int pre = ptr;

            while (n[pre].next != NULL) {
                ptr2 = n[pre].next;
                if (c.compare(n[ptr].data, n[ptr2].data) == 0) {
                    remove(ptr2);
                    count++;
                } else
                    pre = ptr2;
            }
            if (count == 0)
                ptr = n[ptr].next;
            else {
                int temp = n[ptr].next;
                remove(ptr);
                ptr = temp;
            }
        }
        current = head;
    }
}
