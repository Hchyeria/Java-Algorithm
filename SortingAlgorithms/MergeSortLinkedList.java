package SortingAlgorithms;

// Given a singly-linked list, where each node contains an integer value, sort it in ascending order.
// The merge sort algorithm should be used to solve this problem.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
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

public class MergeSortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMedium(head);
        ListNode right = mid.next;
        // be careful about this, set the next value equal to null
        mid.next = null;
        ListNode leftPart = sortList(head);
        ListNode rightPart = sortList(right);
        return merge(leftPart, rightPart);
    }


    private ListNode findMedium(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == right) {
            return left;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        cur.next = (left == null) ? right : left;

        return dummy.next;
    }
}

// Time complexity is O(n*log(n)).
// Space complexity is O(log(n)), because of call-stack.
