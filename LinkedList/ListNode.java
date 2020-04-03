package LinkedList;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode (int value) {
        this.val = value;
        this.next = null;
    }

    public ListNode (ListNode  node) {
        this.val = node.val;
        this.next = node.next;
    }
    public ListNode(int[] array) {
        // assumption: array != null && array.length > 0
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        this.val = dummy.next.val;
        this.next = dummy.next.next;
    }
    @Override
    public String toString() {
        ListNode head = new ListNode(this.val);
        head.next = this.next;
        String s = "" + head.val;
        while (head.next != null) {
            s += " -> " + head.next.val;
            head = head.next;
        }
        return s;
    }
}