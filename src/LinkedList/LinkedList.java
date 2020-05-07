import java.util.Comparator;

public class LinkedList<E> {

    public static class Node<E> {
        private final E data;
        private Node<E> next;

        // constructor
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next; // 다음 노드 참조(뒤쪽 포인터)
        }
    }

    private Node<E> head; // 머리 노드
    private Node<E> crnt; // 현재 선택한 노드 (검색한 노드를 선택, 삭제하는 등의 용도)
    // if list is emptyu: (head==null)
    // if list has one node only: head.next == null
    // if list has two nodes : head.next.next == null

    // constructor
    public LinkedList() { this.head = crnt = null; }

    // 노드 검색
    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head; // 현재 스캔중인 노드

        while (ptr != null) {
            if (c.compare(obj, ptr.data) == 0) { // 검색 성공
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;  // 다음 노드 선택
        }
        return null;         // 검색 실패
    }

    /**
     * 머리에 노드 삽입
     * 만약 비어있는 리스트에 노드를 삽입할 경우, 현재 head==null 이므로 자동으로 삽입된 노드의 next = null이다.
     * */
    public void addFirst(E obj) {
        Node<E> ptr = head; // 삽입 전의 머리 노드
        head = crnt = new Node<>(obj, ptr);
    }

    public void addLast(E obj) {
        if (head == null) {
            addFirst(obj);
        } else {
            Node<E> ptr = head;
            while (ptr.next != null) ptr = ptr.next; // while 문 종료되면 ptr은 꼬리 노드를 가리킴
            ptr.next = crnt = new Node<>(obj, null);
        }
    }

    public void removeFirst() {
        if (head != null) // 리스트가 비어있지 않은 경우에만 실행됨
            head = crnt = head.next;
    }

    public void removeLast() {
        if (head != null) {
            if (head.next == null) { // 노드가 하나뿐이면 머리노드를 삭제
                removeFirst();
            } else {
                Node<E> ptr = head; // 스캔 중인 노드
                Node<E> pre = head; // 스캔 중인 노드의 앞쪽 노드

                while (ptr.next != null) {
                    pre = ptr;
                    ptr = ptr.next;
                }
                pre.next = null; // pre = 삭제 후의 꼬리노드, 기존의 꼬리노드는 어디에서도 참조하지 않기 때문에 자동 해제됨
                crnt = pre;
            }
        }
    }

    public void remove(Node<E> p) {
        if (head != null) {
            if (p == head) {
                removeFirst();
            } else {
                Node<E> ptr = head;

                while (ptr.next != p) {
                    ptr = ptr.next;
                    if (ptr == null) return; // p는 리스트에 존재하지 않음
                }
                ptr.next = p.next; // p를 건너뛰고 ptr과 p의 다음 노드를 연결 (참조되지 않는 노드 p는 자동해제)
                crnt = ptr;
            }
        }
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        remove(crnt); // 삭제 후 crnt는 삭제한 노드의 앞쪽 노드를 참조
    }

    // 모든 노드를 삭제
    public void clear() {
        while (head != null) removeFirst();
        crnt = null;
    }

    // 선택 노드를 하나 뒤쪽으로 이동
    public boolean next() {
        if (crnt == null || crnt.next== null) return false; // 이동 불가
        crnt = crnt.next;
        return true;
    }

    public void printCurrentNode() {
        if (crnt == null) System.out.println("선택한 노드가 없습니다.");
        else System.out.println(crnt.data);
    }

    public void dump() {
        Node<E> ptr = head;

        while (ptr != null) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    // comparator c에 의해 서로 같다고 보는 노드를 찾아 가장 앞쪽의 노드를 남기고 모두 삭제
    public void purge(Comparator<? super E> c) {
        Node<E> ptr = head;

        while (ptr != null) {
            int count = 0;
            Node<E> ptr2 = ptr;
            Node<E> pre =  ptr;

            while (pre.next != null) {
                ptr2 = pre.next;
                if (c.compare(ptr.data, ptr2.data) == 0) {
                    pre.next = ptr2.next;
                    count++;
                } else {
                    pre = ptr2;
                }
            }

            // 앞의 노드만 남겨두고 중복되는 나머지 노드를 삭제
            ptr = ptr.next;

            // 아래 코드로 바꾸면 : 중복이 있는 모든 노드를 삭제
//            if (count == 0) {
//                ptr = ptr.next;
//            } else {
//                Node<E> temp = ptr;
//                remove(ptr);
//                ptr = temp.next;
//            }
        }
        crnt = head;
    }

}
