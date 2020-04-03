package LinkedList;

/*
 * Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null

 */

public class InsertInSortedLinkedList {
    public ListNode insert(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        // when we con't definitely determine the head
        // then we use dummy node to simplify our code
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        /*
        when we don't use dummy node, our code will like this
        if (head == null || head.value > value) {
            newNode.next = head;
            head = newNode;
        }
        */
        // up to cur.next == null || cur.next.value >= value
        ListNode cur = dummy;
        while (cur.next != null && cur.next.val < value) {
            cur = cur.next;
        }

        newNode.next = cur.next;
        cur.next = newNode;

        return dummy.next;
    }
}
