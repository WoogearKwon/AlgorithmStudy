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
            return;             // key가 이진검색트리에 이미 있음
        else if (cond < 0) { // 음수: key < node.getKey()
            if (node.left == null)
                node.left = new Node<>(key, data, null, null);
            else
                addNode(node.left, key, data); // 왼쪽 서브트리로 추가
        } else {
            if (node.right == null)
                node.right = new Node<>(key, data, null, null);
            else
                addNode(node.right, key, data); // 오른쪽 서브트리로 추가
        }
    }

    public void add(K key, V data) {
        if (root == null)
            root = new Node<>(key, data, null, null);
        else
            addNode(root, key, data);
    }

    // 키 값이 key인 노드를 삭제 (과정이 복잡함)
    public boolean remove(K key) {
        Node<K, V> p = root;                    // 스캔 중인 노드
        Node<K, V> parent = null;               // 스캔 중인 노드의 부모 노드
        boolean isLeftChild = true;             // p는 parent의 왼쪽 자식 노드인가?

        while (true) {                          // *** 노드 찾기 (p의 키값이 key와 동일할 때까지 반복) ***
            if (p == null)                      // 더 이상 진행하지 않으면
                return false;                   // 그 키 값은 없음
            int cond = comp(key, p.getKey());   // key와 노드 p의 키 값을 비교
            if (cond == 0)                      // 같으면
                break;                          // 검색 성공
            else {
                parent = p;                     // 가지로 내려가기 전에 부모를 설정
                if (cond < 0) {                 // key 쪽이 작으면
                    isLeftChild = true;         // 왼쪽 자식으로 내려감
                    p = p.left;                 // 왼쪽 서브트리에서 검색
                } else {                        // key 쪽이 크면
                    isLeftChild = false;        // 오른쪽 자식으로 내려감
                    p = p.right;                // 오른쪽 서브 트리에서 검색
                }
            }
        }

        if (p.left == null) {                   // 1. 왼쪽 자식이 없는 경우 (양쪽 자식이 모두 없는 경우도 해당)
            if (p == root)
                root = p.right;
            else if (isLeftChild)
                parent.left = p.right;          // 부모의 왼쪽 포인터가 오른쪽 자식을 가리킴
            else
                parent.right = p.right;         // 부모의 오른쪽 포인터가 오른쪽 자식을 가리킴

        } else if (p.right == null) {           // 2. 오른쪽 자식이 없는 경우
            if (p == root)
                root = p.left;
            else if (isLeftChild)
                parent.left = p.left;           // 부모의 왼쪽 포인터가 왼쪽 자식을 가리킴
            else
                parent.right = p.left;          // 부모의 오른쪽 포인터가 왼쪽 자식을 가리킴

        } else {                                // 양쪽 자식이 모두 있는 경우
            parent = p;
            Node<K, V> left = p.left;           // 서브 트리 가운데 가장 큰 노드
            isLeftChild = true;
            while (left.right != null) {        // 가장 큰 노드 left를 검색 (left.right이 없을 때 까지 반복)
                parent = left;
                left = left.right;
                isLeftChild = false;
            }
            p.key = left.key;                   // left의 키 값을 p로 옮김
            p.data = left.data;                 // left의 데이터를 p로 옮김
            if (isLeftChild)
                parent.left = left.left;        // left를 삭제
            else
                parent.right = left.left;       // left를 삭제
        }
        return true;
    }

    // node를 루트로 하는 서브 트리의 노드를 키 값의 오름차순으로 출력
    // 출력순서: 왼쪽 자식 ->부모 -> 오른쪽 자식
    private void printSubTree(Node node) {
        if (node != null) {
            printSubTree(node.left);							// 왼쪽 서브 트리를 키 값의 오름차순으로 출력
            System.out.println(node.key + " " + node.data);		// node를 출력
            printSubTree(node.right);							// 오른쪽 서브 트리를 키 값의 오름차순으로 출력
        }
    }

    public void printSubTreeDown(Node node) {
        if (node != null) {
            printSubTreeDown(node.right);
            System.out.println(node.key + " " + node.data);
            printSubTreeDown(node.left);
        }
    }

    // 모든 노드를 키 값의 오름차순으로 출력
    public void print() {
        printSubTree(root);
    }

    // 모든 노드를 키 값의 오륾차순으로 출력
    public void printReverse() {
        printSubTreeDown(root);
    }
}
