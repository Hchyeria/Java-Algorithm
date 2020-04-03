package LinkedList;


public class MiddleNodeOfLinkedList {
    private ListNode findMedium(ListNode head) {
        if(head == null) {
            return null;
        }

        ListNode fast = head, slow = head;
        // the condition fast.next.next != null make sure to return the first middle node when the length of LinkedList is even(偶数)
        // if we change it to fast != null && fast.next != null
        // then [1,2,3,4,5,6] -> 4
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).