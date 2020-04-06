package Stack;

import java.util.List;

class ListNode {
    public int value;
    public ListNode next;

    public ListNode (int value) {
        this.value = value;
        this.next = null;
    }

    public ListNode (ListNode node) {
        this.value = node.value;
        this.next = node.next;
    }
}

public class Stack {
    private ListNode head; // dummy node
    public Stack() {
        head = new ListNode(0);
        head.next = null;
    }
    public int push(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head.next;
        head.next = newNode;
        return value;
    }

    public Integer pop() {
        ListNode temp = head.next;
        if (temp == null) {
            return null;
        }
        head.next = temp.next;
        temp.next = null;
        return temp.value;
    }

    public Integer peek() {
        return head.next != null ? head.next.value : null;
    }
}
