package LinkedList;

// Corner case:
//      head == null (zero element)
//      head.next == null (one element)
// usually need to consider:
//      while loop - we need to understand what the terminate condition


public class LinkedList {
    public int length(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public ListNode get(int index, ListNode head) {
        // Java is pass by value
        // pass a copy of the reference
        while (index > 0 && head != null) {
            head = head.next;
            index--;
        }
        // index <= 0 || head == null
        return head;
    }

    public ListNode appendHead(ListNode head, int value) {
        ListNode newHead = new ListNode(value);
        newHead.next = head;
        return newHead;
    }

    public ListNode appendTail(ListNode head, int value) {
        if (head == null) {
            return new ListNode(value);
        } else {
            ListNode cur = head;
            // although cur is changing
            // but the head not change
            // cur is the copy of the head reference
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(value);
        }
        return head;
    }
    public ListNode move(ListNode head, int value) {
        if (head == null || head.val == value) {
            return null;
        } else {
            ListNode cur = head;

            while (cur.next != null && cur.next.val != value) {
                cur = cur.next;
            }
            if (cur.next != null) {
                cur.next = cur.next.next;
            }
        }
        return head;
    }
}

