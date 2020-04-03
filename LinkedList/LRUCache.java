package LinkedList;

// Implement a least recently used cache. It should provide set(), get() operations.

// If not exists, return null.

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int CAPACITY;
    private Node<K, V> head;
    private Node<K, V> tail;
    private Map<K, Node<K, V>> cache;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        cache = new HashMap<>();
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        remove(node);
        insert(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.isEmpty()) {
            head = new Node<>(key, value);
            tail = new Node<>(key, value);
            head.next = tail;
            tail.prev = head;
        }
        Node<K, V> node = cache.get(key);
        if (node != null) {
            // remember update the value
            node.value = value;
            remove(node);
        } else if (cache.size() < CAPACITY) {
            node = new Node<>(key, value);
        } else {
            node = head.next;
            remove(node);
            node.update(key, value);
        }
        insert(node);
    }

    private Node<K, V> insert(Node<K, V> node) {
        cache.put(node.key, node);
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        return node;
    }

    private Node<K, V> remove(Node<K, V> node) {
        cache.remove(node.key, node);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        return node;
    }
}
