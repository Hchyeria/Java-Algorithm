package Recursion;

public class PrintIfElseBlockDFS {
    public void printBlocks(int n) {
        char[] array = new char[n * 2];
        helper(array, n, n, 0);
    }

    private void helper(char[] array, int left, int right, int pos) {
        if (pos == array.length && left == 0 && right == 0) {
            printSolution(array, 0);
            return;
        }
        if (left < 0 || right < left) {
            return;
        }
        if (left > 0) {
            array[pos] = '{';
            helper(array, left - 1, right, pos + 1);
        }
        if (right > left) {
            array[pos] = '}';
            helper(array, left, right - 1, pos + 1);
        }
    }

    private void printSolution(char[] array, int heading) {
        for (char c : array) {
            if (c == '{') {
                printSpace(heading);
                System.out.println("if {");
                heading += 2;
            } else {
                heading -= 2;
                printSpace(heading);
                System.out.println('}');
            }
        }
    }

    private void printSpace(int heading) {
        for (int i = 0; i < heading; i++) {
            System.out.print(" ");
        }
    }

    // Time complexity: O(2^(2*n))
    // Space complexity: O(n)


    public static void main(String[] args) {
        PrintIfElseBlockDFS test = new PrintIfElseBlockDFS();
        test.printBlocks(3);
    }
}
