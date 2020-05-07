package Tree;

import java.util.Comparator;

public class BinarySearchTree<K, V> {

    static class Node<K, V> {
        private K key;
        private V data;
        private Node<K, V> left;
        private Node<K, V> right;

        Node(K key, V data, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return data;
        }

        void print() {
            System.out.println(data);
        }
    }

    private Node<K, V> root;
    private Comparator<? super K> comparator = null;

    // 이 생성자로 생성한 이진검색트리에서는 노드 키값의 대소 관계를 자연 순서에 따라 수행
    // 따라서 키를 나타내는 K의 형(type)이 Comparable 인터페이스를 구현하고 있는 Integer나 String등에 알맞음
    public BinarySearchTree() {
        root = null;
    }

    // 인수로 비교자를 전달받는 생성자
    // 전달받은 비교자에 의해 대소 관계 판단
    // this()에 의해 인수를 전달받지 않은 생성자 BinTree()를 호출
    // root가 비어있는 이진검색트리를 생성
    public BinarySearchTree(Comparator<? super K> c) {
        this();
        comparator = c;
    }

    /**
     * key1과 key2를 비교하여 아래 값을 반환
     * key1 > key2 => 양수
     * key1 < key2 => 음수
     * key == key2 => 0
     * */
    private int comp(K key1, K key2) {
        return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2) : comparator.compare(key1, key2);

        // (Comparable<K>)key1).compareTo(key2)의 비교 방식:
        // key1을 Comparable<K> 인터페이스형으로 형변환 하고 compareTo 메서드를 호출하여 key2와 비교
    }

    public V search(K key) {
        Node<K, V> p = root;

        while (true) {
            if (p == null)
                return null;           // 검색 실패

            int cond = comp(key, p.getKey());
            if (cond == 0)
                return p.getValue();  // 검색 성공
            else if (cond < 0)
                p = p.left;           // key 쪽이 작으면 왼쪽 서브트리 검색
            else
                p = p.right;          // key 쪽이 크면 오른쪽 검색
        }
    }

    private void addNode(Node<K,V> node, K key, V data) {
        int cond = comp(key, node.getKey());
        if (cond == 0)
            return;
        else if (cond < 0) {
            if (node.left == null)
                node.left = new Node<K,V>(key, data, null, null);
            else
                addNode(node.left, key, data);
        } else {
            if (node.right == null)
                node.right = new Node<K,V>(key, data, null, null);
            else
                addNode(node.right, key, data);
        }
    }
}
