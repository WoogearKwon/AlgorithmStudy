package practices.linked_list;

import java.util.Comparator;

/** 원형 이중 연결리스트 */
public class DoubleLinkedList<E> {

    class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        // 비어있는(dummy) 노드를 만들기 위한 생성자
        public Node() {
            data = null;
            prev = next = this;
        }

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> current;

    // constructor
    public DoubleLinkedList() {
        head = current = new Node<E>();
    }

    public boolean isEmpty() {
        return head.next == head;
    }

    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head.next;    // 현재 스캔중인 노드: head는 더미노드이기 때문에 그 다음 노드부터 스캔

        while (ptr != head) {
            if (c.compare(obj, ptr.data) == 0) {
                current = ptr;
                return ptr.data;
            }

            ptr = ptr.next;
        }
        return null;
    }

    public void printCurrentNode() {
        if (isEmpty())
            System.out.println("선택 노드가 없습니다.");
        else
            System.out.println(current.data);
    }

    public void dump() {
        Node<E> pointer = head.next;

        while (pointer != head) {
            System.out.println(pointer.data);
            pointer = pointer.next;
        }
    }

    public void dumpReverse() {
        Node<E> pointer = head.prev;

        while (pointer != head) {
            System.out.println(pointer.data);
            pointer = pointer.prev;
        }
    }

    // 선택노드를 하나 뒤쪽으로 이동
    public boolean next() {
        if (isEmpty() || current.next == head)
            return false; // 이동할 수 없음
        current = current.next;
        return true;
    }

    // 선택 노드를 하나 앞쪽으로 이동
    public boolean prev() {
        if (isEmpty() || current.prev == head)
            return false; // 이동할 수 없음
        current = current.prev;
        return true;
    }

    public void add(E obj) {
        Node<E> node = new Node<E>(obj, current, current.next);
        current.next = current.next.prev = node;
        current = node;
    }

    public void addFirst(E obj) {
        current = head;
        add(obj);
    }

    public void addLast(E obj) {
        current = head.prev;
        add(obj);
    }

    public void removeCurrentNode() {
        if (!isEmpty()) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = current.prev;
            if (current == head) current = head.next;
        }
    }

    public void remove(Node p) {
        Node<E> pointer = head.next;

        while (pointer != head) {
            if (pointer == p) {
                current = p;
                removeCurrentNode();
                break;
            }
            pointer = pointer.next;
        }
    }

    public void removeFirst() {
        current = head.next;
        removeCurrentNode();
    }

    public void removeLast() {
        current = head.prev;
        removeCurrentNode();
    }

    public void clear() {
        while (!isEmpty())
            removeFirst();
    }


}
