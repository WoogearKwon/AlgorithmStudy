package practices.etc;

import java.util.NoSuchElementException;

public class Queue<T> {
    private Node<T> first;
    private Node<T> last;

    class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T data) {
        Node<T> t = new Node(data);

        if (last != null) {
            last.next = t;
        }

        last = t;

        if (first == null) {
            first = last;
        }
    }

    public T remove() {
        if (first == null) throw new NoSuchElementException();

        T data = first.data;
        first = first.next;

        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();

        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
