package Stack;

/*
 *  Data Structure
Stack With min()
Enhance the stack implementation to support min() operation.
min() should return the current minimum['mɪnɪməm] value in the stack.
If the stack is empty, min() should return -1.

pop() - remove and return the top element, if the stack is empty, return -1
push(int element) - push the element to top
top() - return the top element without remove it, if the stack is empty, return -1
min() - return the current min value in the stack.

 */
/*
 *    stack cur:       || 2 4 1 1 5
 *    stack global_min || 2 2 1 1 1
 */

import java.util.LinkedList;

public class QueueByTwoStacks {
    private LinkedList<Integer> input;
    private LinkedList<Integer> output;

    public QueueByTwoStacks() {
        input = new LinkedList<>();
        output = new LinkedList<>();
    }

    public void move() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.offerFirst(input.pollFirst());
            }
        }
    }

    public Integer poll() {
        if (isEmpty()) {
            return null;
        }
        move();
        return output.pollFirst();
    }

    // Time complexity is O(n) in the worst case, but is O(1) amortized time.

    public void offer(int element) {
        input.offerFirst(element);
    }

    // Time complexity is O(1).

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        move();
        return output.peekFirst();
    }

    // Time complexity is O(n) in the worst case, but is O(1) amortized time.

    public int size() {
        return input.size() + output.size();
    }

    // Time complexity is O(1).

    public boolean isEmpty() {
        return input.size() == 0 && output.size() == 0;
    }

    // Time complexity is O(1).
}
