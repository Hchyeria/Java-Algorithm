package LinkedList;

// Check if a given linked list has a cycle. Return true if it does, otherwise return false.

public class CheckCycleLinkedList {
    public boolean hasCycle(ListNode head) {
        // if there is only one node
        // it also can be chained
        if (head == null) {
            return false;
        }
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return fast == slow;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).

/*
* Follow up:
*  return the start node of the circle (which is the next of the tail)
* Answer:
*  remember that the fast pointer is always twice speed than the slow
*  so the distance from head to slow if equal to from slow to fast (half of all)
* */
/*

    ListNode fast = head, slow = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            break;
        }
    }
    if (fast == null || fast.next == null) {
        return null;
    }
    // then move the slow to the head
    // and move the slow and fast pointer step by step synchronously
    // when there is a collision happen, the current node of the slow is our answer
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }

    return slow;
*/
