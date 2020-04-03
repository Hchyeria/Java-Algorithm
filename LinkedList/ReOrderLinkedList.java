package LinkedList;


/*
 * Reorder the given singly-linked list
 * 			N1 -> N2 -> N3 -> N4 -> … -> Nn -> null
 * 	  to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null

Examples

L = null, is reordered to null
L = 1 -> null, is reordered to 1 -> null
L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
L = 1 -> 2 -> 3 -> null, is reordered to 1 -> 3 -> 2 -> null
 */

/*
 * Basic idea:
 * 		step1: find the middle node of the original LinkedList and split the LinkedList to two halfs by middle node;
 * 		step2: reverse second half LinkedList
 * 		step3: merge first half with reversed second half;
 *
 */


public class ReOrderLinkedList {
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode midNode = findMiddle(head);
        ListNode right = reverse(midNode.next);
        // don't forget this!!!!!!!!!!!!!
        // split it into two!!
        midNode.next = null;
        return merge(head, right);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode nextNode = head.next;
        ListNode newHead = reverse(nextNode);
        nextNode.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (head1 != null && head2 != null) {
            cur = insertAfter(cur, head1);
            head1 = head1.next;

            cur = insertAfter(cur, head2);
            head2 = head2.next;
        }

        cur.next = head1 == null ? head2 : head1;

        return dummy.next;
    }

    private ListNode insertAfter(ListNode cur, ListNode node) {
        cur.next = node;
        cur = cur.next;
        return cur;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).