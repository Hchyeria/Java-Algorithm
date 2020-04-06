package Stack;

import java.util.Arrays;
import java.util.LinkedList;

// Sort an array with two stacks.

// Assumption: with duplicate elements, without duplicate elements?


public class SortArrayWithTwoStacks {
    // you should understand clearly what are the two stacks responsible for ?
    // and make their function independent and consistent
    private LinkedList<Integer> stack1; // store the unsorted elements
    private LinkedList<Integer> stack2; // store have already be sorted elements

    public SortArrayWithTwoStacks() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public int[] sort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        for (int value : array) {
            stack1.push(value);
        }

        // do select sort
        while (!stack1.isEmpty()) {
            int globalMin = Integer.MAX_VALUE;
            int counter = 0;
            while (!stack1.isEmpty()) {
                int temp = stack1.pop();
                if (temp < globalMin) {
                    globalMin = temp;
                    // remember reset the counter !!
                    counter = 0;
                }
                if (temp == globalMin) {
                    counter++;
                }
                stack2.push(temp);
            }

            while (!stack2.isEmpty() && stack2.peek() >= globalMin) {
                int temp = stack2.pop();
                if (temp > globalMin) {
                    stack1.push(temp);
                }
            }
            while (counter-- > 0) {
                stack2.push(globalMin);
            }
        }

        for (int i = array.length - 1; i >= 0; --i) {
            array[i] = stack2.pop();
        }

        return array;
    }



    // Time complexity is O(n^2).
    // Space complexity is O(n).

    public static void main(String args[]) {
            SortArrayWithTwoStacks sortArrayWithTwoStacks = new SortArrayWithTwoStacks();
            int[] array = null;
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 1 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 3, 1 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 2, 1, 3 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 1, 4, 3, 2 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 1, 5, 2, 4, 3 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 3, 5, 2, 4, 1, 6 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 3, 2, 1, 2, 3, 3, 4 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 1, 1, 1, 5, 4, 3, 6, 2 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
            array = new int[] { 1, 3, 4, 4, 5, 5, 3, 1, 2 };
            System.out.println(Arrays.toString(sortArrayWithTwoStacks.sort(array)));
    }
}
