package LinkedList;

/*
 * Given a linked list and a target value T, partition it such that all nodes less than T
 * are listed before the nodes larger than or equal to target value T.
 * The original relative order of the nodes in each of the two partitions should be preserved.

Examples

L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3,
is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
 */


/*
 *  example:   L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3
 *  	step1: initialize two LinkedLists heads;
 *
 *  	step2:
 *  		Iterate each element in the list and compare the current node's value to the target's value
 *  			case1:
 *  				if current node's value < target's value, add the current node to the tail of first LinkedList;
 *  			case2:
 *  				else, add the current node to the tail of second LinkedList;
 *  		The result should like below:
 *  			LinkedList1: to store nodes less than target     			  2 -> 1
 *  			LinkedList2: to store nodes greater than or equal to target   4 -> 3 -> 5
 *
 *  	step3: Concatenate the tail of first half to the head of second half
 *  			2 -> 1 -> 4 -> 3 -> 5;
 *  	step4: Do not forget to add null to the tail of second half;
 *  			2 -> 1 -> 4 -> 3 -> 5 -> null;
 */


public class PartitionLinkedList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyFirst = new ListNode(0);
        ListNode firstTail = dummyFirst;
        ListNode dummySecond = new ListNode(0);
        ListNode secondTail = dummySecond;

        while (head != null) {
            if (head.val < x) {
                firstTail.next = head;
                firstTail = firstTail.next;
            } else {
                secondTail.next = head;
                secondTail = secondTail.next;
            }
            head = head.next;
        }
        // don't forget this !!!
        // if not, the list may have a cycle!
        secondTail.next = null;

        firstTail.next = dummySecond.next;
        return dummyFirst.next;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).
