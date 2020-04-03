package LinkedList;

// Given an unlimited stream of characters, find the first non-repeating
// character from the stream in O(1) time at any moment

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacterInStream {
    private static class Node {
        char key;
        Node pre, next;

        Node() { }
        Node(char k) {
            key = k;
        }
    }

    private final Node head = new Node();
    private final Node tail = new Node();
    private Map<Character, Node> map;
    public FirstNonRepeatingCharacterInStream() {
        map = new HashMap<>();
        head.next = tail;
        tail.pre = head;
    }

    public void read(char ch) {
        if (!map.containsKey(ch)) {
            insert(new Node(ch));
        } else {
            Node node = map.get(ch);
            if (node != null) {
                remove(node);
            }
        }
    }

    private void insert(Node node) {
        map.put(node.key, node);
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
        node.next = tail;
    }

    private void remove(Node node) {
        map.put(node.key, null);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = node.next = null;
    }

    public Character firstNonRepeating() {
        if (head.next == tail) {
            return null;
        }
        return head.next.key;
    }
}
