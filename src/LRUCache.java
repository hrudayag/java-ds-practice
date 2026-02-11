import java.util.HashMap;

public class LRUCache {

    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);

        if (node == null) {
            return -1;
        }

        // Move accessed node to the end (most recently used)
        detach(node);
        addBeforeTail(node);

        return node.value;
    }

    public void put(int key, int value) {

        Node node = cache.get(key);

        if (node != null) {
            node.value = value;
            detach(node);
            addBeforeTail(node);
            return;
        }

        if (cache.size() == capacity) {
            Node leastUsed = head.next;
            detach(leastUsed);
            cache.remove(leastUsed.key);
        }

        Node newNode = new Node(key, value);
        addBeforeTail(newNode);
        cache.put(key, newNode);
    }

    private void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addBeforeTail(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 100);
        cache.put(2, 200);

        System.out.println("Get 1: " + cache.get(1));

        cache.put(3, 300); // should remove key 2

        System.out.println("Get 2: " + cache.get(2));
        System.out.println("Get 3: " + cache.get(3));
    }
}
