package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an integer n, return a list of Strings, each representing
// one possibility of if blocks.

public class AllIfBlocks {

    public List<String> allIfBlocks(int n) {
        char[] array = new char[n * 2];
        List<String> result = new ArrayList<String>();
        helper(array, n, n, 0, result);
        return result;
    }

    private void helper(char[] array, int left, int right, int index, List<String> result) {
        if (index == array.length && left == 0 && right == 0) {
            result.add(toResult(array));
            return;
        }
        if (left > 0) {
            array[index] = '{';
            helper(array, left - 1, right, index + 1, result);
        }
        if (right > left) {
            array[index] = '}';
            helper(array, left, right - 1, index + 1, result);
        }

    }

    private String toResult(char[] array) {
        StringBuilder sb = new StringBuilder();
        int space = 0;
        for (char c : array) {
            if (c == '{') {
                for (int i = 0; i < space; ++i) {
                    sb.append(' ');
                }
                sb.append("if {\n");
                space += 2;
            }  else {
                space -= 2;
                for (int i = 0; i < space; ++i) {
                    sb.append(' ');
                }
                sb.append("}\n");
            }
        }
        return sb.toString();
    }

    // Time complexity is O(2^(2*n)) * O(n^3), why n^3 ??????
    // because string concatenation
    // Space complexity is O(n).

    public List<String> allIfBlocks2(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> array = new ArrayList<>();
        List<String> result = new ArrayList<>();
        helper2(array, n, n, result);
        return result;
    }

    private void helper2(List<String> array, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(toResult2(array));
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (left > 0) {
            for (int i = 0; i < right - left; ++i) {
                sb.append(" ");
            }
            array.add(sb.append("if {\n").toString());
            helper2(array, left - 1, right, result);
            array.remove(array.size() - 1);
        }
        // remember reset the StringBuilder
        sb.setLength(0);
        if (right > left) {
            for (int i = 0; i < right - left - 1; ++i) {
                sb.append(" ");
            }
            array.add(sb.append("}\n").toString());
            helper2(array, left, right - 1, result);
            array.remove(array.size() - 1);
        }

    }

    private String toResult2(List<String> array) {
        StringBuilder res = new StringBuilder();
        for (String s : array) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AllIfBlocks allIfBlocks = new AllIfBlocks();
        List<String> result = allIfBlocks.allIfBlocks2(3);
        for (String s : result) {
            System.out.println(s);
        }
    }

    // If array is used, usually needs position/level to keep track of current
    // level of tree, and don't need to remove.
    // If List/StringBuilder is used, position/level is not needed (use size of
    // List/StringBuilder), but need to remove.
}
