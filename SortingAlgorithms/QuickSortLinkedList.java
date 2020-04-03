package SortingAlgorithms;

public class QuickSortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        head = partition(head);
        return head;
    }


    private ListNode partition(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pivot = head;
        ListNode dummyFirst = new ListNode(0);
        ListNode firstTail = dummyFirst;
        ListNode dummySecond = new ListNode(0);
        ListNode secondTail = dummySecond;
        head = head.next;
        while (head != null) {
            if (head.val < pivot.val) {
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

        // remember the fistTail.next = null
        // we want to recurse the left side
        // so we need to split it totally into two parts
        firstTail.next = null;
        ListNode left = partition(dummyFirst.next);
        ListNode right = partition(dummySecond.next);

        ListNode leftTail = getTail(left); // this step will cause more time
        pivot.next = right;
        if (leftTail != null) {
            leftTail.next = pivot;
            return left;
        }
        return pivot;
    }

    private ListNode getTail(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        while (head.next != null) {
            head = head.next;
        }

        return head;
    }

    public static void main(String args[]) {
        ListNode array = new ListNode(new int[] {1, 8 , 2 ,6 , 3, 4 ,2 ,9});
        QuickSortLinkedList quickSortLinkedList = new QuickSortLinkedList();
        ListNode sorted = quickSortLinkedList.sortList(array);
        System.out.println(sorted.toString());
    }

}
