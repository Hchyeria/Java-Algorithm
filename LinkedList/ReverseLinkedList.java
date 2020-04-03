package LinkedList;

/*
    Reverse a singly-linked list.

    Examples

    L = null, return null
    L = 1 -> null, return 1 -> null
    L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
*/



import java.util.LinkedList;
import java.util.List;

public class ReverseLinkedList {
    // iterative solution
    public ListNode reverse(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
       /*
            1 -> 2 -> 3 -> null
            pre  cur  next
                pre  cur   next
                     pre   cur  next
       */
       ListNode pre = null;
       while (head != null) {
           ListNode nextNode = head.next;
           head.next = pre;
           pre = head;
           head = nextNode;
       }
       return pre;
    }

    // Time complexity is O(n).
    // Space complexity is O(1).

    // recursive solution
    public ListNode reverse2(ListNode head) {
        /*	  to reverse 1 -> 2 -> 3 -> null  we need to reverse 2 -> 3 -> null .... after recursion  3 -> 2 -> 1 -> null
         *
         *    1 -> 2 -> 3 -> null
         *    1 -->   2 -> 3 -> null
         *    1 -->   2 -->   <- 3
         *    1 --> 2 <- 3
         *    null <- 1 <- 2 <- 3
         *
         *    and the base case should be head == null or head.next == null
         */
        if (head == null || head.next == null) {
            return head;
        }

        // the reverse order is back-forward because of the call stack
        // each recursion is the following
        //      nextNode.next = head;
        //        head.next = null;
        // the most deep call stack return the last ListNode as the new head
        // the each result of recursion is not definitely to be used as the upper stack
        // the recursion has the function of iteration,
        // it does not have to return something that be used in the upper stack

        ListNode nextNode = head.next;
        ListNode newHead = reverse2(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }

    // Time complexity is O(n).
    // Space complexity is O(n), because of call-stack.

    private void toString(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String args[]) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);

        head.next = head2;
        head2.next = head3;
        head3.next = head4;


        reverseLinkedList.toString(reverseLinkedList.reverse2(head));
    }
}
