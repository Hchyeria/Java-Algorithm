package Queue;

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

public class Queue {
    private  ListNode head;
    private ListNode tail;
    public Integer offer(int value) {
        if (tail == null) {
            head = tail = new ListNode(value);
            return value;
        }
        tail.next = new ListNode(value);
        tail = tail.next;
        return value;
    }

    public  Integer poll() {
        if (head == null) {
            return null;
        }

        ListNode temp = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        temp.next = null;
        return temp.value;
    }

    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

}
