package LinkedList;

/*
 *Merge two sorted lists into one large sorted list.

Examples

L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
L1 = null, L2 = null, merge L1 and L2 to null

 */

/*
 * basic idea: we don't know which node will be the head of merged linkedlist,
 * so we have to use a dummy head for return,
 * in addition, we still have to use another node reference(cur) to maintain the current tail of merged linkedlist
 * to judge the next-merge-node belongs to which linkedlist
 * and do not forget to check if one of the linkedlist meets null already
 */

public class MergeTwoSortedLinkedList {
    private ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        ListNode dummy = new ListNode(0), cur = dummy ;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;

        return dummy.next;
    }
}

// Time complexity is O(m + n).
// Space complexity is O(1).