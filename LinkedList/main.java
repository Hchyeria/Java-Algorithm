package LinkedList;

import java.util.List;

public class main {
    public ListNode mid(ListNode head) {
        if (head == null) {
            return head;
        }
        // head != null, head.next != null
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // the slow pointer always point the left side when the length is odd
        return slow;
    }


}
