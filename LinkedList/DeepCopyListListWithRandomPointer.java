package LinkedList;

// Each of the nodes in the linked list has another pointer pointing to a random node in the list or null.
// Make a deep copy of the original list.

import java.util.HashMap;
import java.util.Map;

public class DeepCopyListListWithRandomPointer {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node, Node> hashMap = new HashMap<>();
        Node newHead = new Node(head.val);
        hashMap.put(head, newHead);
        Node cur = newHead;
        while (head != null) {
            if (head.next != null) {
                Node nextNode = head.next;
                Node newNextNode = hashMap.get(nextNode);
                if (newNextNode == null) {
                    newNextNode = new Node(nextNode.val);
                    hashMap.put(nextNode, newNextNode);
                }
                cur.next = newNextNode;
            }
            if (head.random != null) {
                Node randomNode = head.random;
                Node newRandomNode = hashMap.get(randomNode);
                if (newRandomNode == null) {
                    newRandomNode = new Node(randomNode.val);
                    hashMap.put(randomNode, newRandomNode);
                }
                cur.random = newRandomNode;
            }
            head = head.next;
            cur = cur.next;
        }
        return newHead;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)

    // Follow up: Can we use O(1) space?
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        while (cur != null) {
            Node nextNode = cur.next;
            Node copyNode = new Node(cur.val);
            cur.next = copyNode;
            copyNode.next = nextNode;
            cur = nextNode;
        }

        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        Node newHead = head.next;
        Node copy = newHead;
        cur = head;
        while (copy.next != null) {
            cur.next = cur.next.next;
            cur = cur.next;

            copy.next = copy.next.next;
            copy = copy.next;
        }
        cur.next = null;

        return newHead;
    }
}
