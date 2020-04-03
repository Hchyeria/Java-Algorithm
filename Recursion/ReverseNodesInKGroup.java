package Recursion;


import LinkedList.ListNode;

public class ReverseNodesInKGroup {
    // recursion way
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }

        if (count == k) {
            ListNode pre = reverseKGroup(cur, k);
            while (count-- > 0) {
                ListNode nextNode = head.next;
                head.next = pre;
                // not
                // head = nextNode;
                // pre = head;
                pre = head;
                head = nextNode;
            }
            return pre;
        }
        return head;
    }

     // iterative way
     public ListNode reverseKGroup2(ListNode head, int k) {
         if (head == null || k <= 0) {
             return head;
         }
         ListNode newHead = null, pre = null;

         ListNode cur = head;
         while (cur != null ) {
             int count = 0;
             ListNode start = cur;
             while (cur != null && count < k) {
                 cur = cur.next;
                 count++;
             }
             if (count == k) {
                 ListNode p = start, nextNode = start.next;
                 while (nextNode != cur) {
                     ListNode tempNext = nextNode.next;
                     nextNode.next = p;
                     p = nextNode;
                     nextNode = tempNext;
                 }
                 if (pre != null) {
                     pre.next = p;
                 } else {
                     newHead = p;
                 }
                 // must add this line
                 // otherwise will creat loop
                 // when [1, 2, 3, 4, 5], k = 5
                 // 1.next = 2, 2.next = 1
                 start.next = null;
                 pre = start;
             } else {
                 if (pre != null) {
                     pre.next = start;
                 } else {
                     newHead = start;
                 }
             }
         }
         return newHead;
     }
}
