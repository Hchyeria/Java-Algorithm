package Queue;


/*
 *  make array look like a circle
 *  assume the array is bounded
 *
 *  step1: if use primitive array
 *       1 2 3 4 5 6 7 8 9 10
 *       head points to first node in queue   poll() head++
 *       tail points next available[ə'veɪləb(ə)l] insertion position in queue offer() tail++
 *
 *       so if tail reaches at array.length - 1, and we invoke poll() to remove several elements, then we invoke offer()?
 *       we will find the previous several positions are wasted because tail is at position
 *       which shows the list is full but actually it isn't.   to solve this: CircularArray
 *
 *  step2: import concept of circular array, making the array look like a circle
 *         head = head + 1 == array.length ? 0 : head + 1  or head = (head + 1) % array.length
 *         tail = tail + 1 == array.length ? 0 : tail + 1  or tail = (tail + 1) % array.length
 *         to keep head(tail) position from out of bound
 *
 *  step3: how to judge the Array is empty or not?
 *  	   because at this step head == tail array can be empty and also can be full
 *
 *  step4: two solutions:
 *  			1.maintain a variable['veriəb(ə)l] called size to record the current element number in the array
 *  			2.head + 1 == tail -> empty (it means at the very beginning the tail is one position preceding[prɪ'sidɪŋ] by the head )
 *  			  head == tail -> full
 *  			  This solution's drawback ['drɔ.bæk] is the first position of array cannot hold element, instead it becomes a flag bit
 *  	          to judge the array is full or empty
 *
 */

public class QueueImplementationByCircularArray {
    public int head;
    public int tail;
    public Integer[] array;

    public QueueImplementationByCircularArray(int cap) {
        array = new Integer[cap];
        head = 0;
        tail = 1;
    }

    public boolean isEmpty() {
        return head + 1 == tail;
    }

    public boolean offer(int ele) {
        if(head == tail) {
            return false;
        }
        array[tail] = ele;
        tail = (tail + 1) % array.length;
        return true;

    }

    public Integer poll() {
        if(isEmpty()) {
            return null;
        }
        Integer res = array[head];
        //array[head] = null;
        head = (head + 1) % array.length;
        return res;
    }

    public Integer peek() {
        if(isEmpty()) {
            return null;
        }
        return array[head];
    }
}
