package Hash;

public class ChainHash<K,V> {

    class Node<K, V> {
        private K key;
        private V data;
        private Node<K, V> next;

        // constructor
        Node(K key, V data, Node<K, V> next) {
            this.key = key;
            this.data = data;
            this.next = next;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return data;
        }

        public int hashCode() {
            return key.hashCode();
        }
    }

    private int size;           // 해시테이블의 크기
    private Node<K, V>[] table;  // 해시 테이블

    // constructor
    public ChainHash(int capacity) {
        try {
            table = new Node[capacity];
            this.size = capacity;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }

    // 해시값을 구함
    public int hashValue(Object key) {  // key 값이 숫자가 아닌 객체일 경우에도 해시값을 생성해서 반환
        return key.hashCode() % size;
    }

    // key값을 갖는 요소 검색 (데이터를 반환)
    public V search(K key) {
        int hash = hashValue(key);      // 검색할 데이터의 해시 값
        Node<K, V> p = table[hash];      // 선택 노드

        while (p != null) {
            if (p.getKey().equals(key))
                return p.getValue();    // 검색 성공
            p = p.next;                 // 다음 노드
        }
        return null;                    // 검색 실패패
    }

    // key, data를 갖는 요소 추가
    public int add(K key, V data) {
        int hash = hashValue(key);
        System.out.println("hash: " + hash);
        Node<K,V> p = table[hash];

        while (p != null) {
            if (p.getKey().equals(key)) // 이미 등록된 키 값
                return 1;
            p = p.next;
        }

        Node<K,V> temp = new Node<K,V>(key, data, table[hash]); // 새 노드를 생성하고 next값에 기존 머리 노드를 삽입
        table[hash] = temp;     // 노드를 삽입
        return 0;
    }

    // key를 갖는 요소 삭제
    public int remove(K key) {
        int hash = hashValue(key);
        Node<K,V> p = table[hash];
        Node<K,V> pp = null;

        while (p != null) {
            if (p.getKey().equals(key)) { // 찾음
                if (pp == null)
                    table[hash] = p.next; // 기존 table[hash]와 p의 연결 끊어줌
                else
                    pp.next = p.next;
            }
            pp = p;
            p = p.next;
        }
        return 1;
    }

    // 해시 테이블을 덤프
    public void dump(){
        for (int i = 0; i < size; i++) {
            Node<K,V> p = table[i];
            System.out.printf("%02d  ", i);
            while (p != null) {
                System.out.printf("→ %s (%s)  ", p.getKey(), p.getValue());
                p = p.next;
            }
            System.out.println();
        }
    }


}
