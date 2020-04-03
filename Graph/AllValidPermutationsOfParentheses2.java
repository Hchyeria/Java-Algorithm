package Graph;

// Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.

// Assumption: l, m, m >= 0

import sun.awt.geom.AreaOp;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AllValidPermutationsOfParentheses2 {
    private static final char[] datas = { '{', '}', '[', ']', '(', ')' };
    public List<List<Character>> allParentheses(int l, int m, int s) {
        // 1st element means input = l pairs of {}
        // 2nd element means left { added so far
        // 3rd element means right } added so far
        int[] braces = { l, 0, 0 };
        int[] brackets = { m, 0, 0 };
        int[] parenthesis = { s, 0, 0 };
        List<List<Character>> res = new ArrayList<>();
        // 0: represents {}
        // 1: represents []
        // 2: represents ()
        Deque<Integer> stack = new LinkedList<>();
        dfs2(res, new int[][] { braces, brackets, parenthesis }, new ArrayList<>(), stack, 2 * (l + m + s));
        return res;
    }

    private void dfs(List<List<Character>> res, int[][] temp,
                     List<Character> prefix, Deque<Integer> stack, int len) {
        if (prefix.size() == len) {
            res.add(new ArrayList<>(prefix));
            return;
        }
        int index = 0;
        for (int[] data : temp) {
            if (data[1] < data[0]) {
                prefix.add(datas[index * 2]);
                stack.offerFirst(index);
                data[1]++;
                dfs(res, temp, prefix, stack, len);

                data[1]--;
                prefix.remove(prefix.size() - 1);
                stack.pollFirst();
            }

            if (data[2] < data[0] && data[2] < data[1] && stack.peekFirst() == index) {
                prefix.add(datas[index * 2 + 1]);
                int needRestor = stack.pollFirst();
                data[2]++;
                dfs(res, temp, prefix, stack, len);

                data[2]--;
                prefix.remove(prefix.size() - 1);
                // don't forget to offer, let the state of stack to be not changed
                stack.offerFirst(needRestor);
            }
            index++;
        }

    }

    // Time complexity: O(6 ^ (l+m+s), although is a only upper bound, not tight
    // because some branches are cut off
    // Space Complexity: O(l+m+s)

    // Follow up: if we impose an additional priority restriction {} > [] > (), then in this case what should we do?
    private void dfs2(List<List<Character>> res, int[][] temp,
                     List<Character> prefix, Deque<Integer> stack, int len) {
        if (prefix.size() == len) {
            res.add(new ArrayList<>(prefix));
            return;
        }
        int index = 0;
        for (int[] data : temp) {
            // the only different from dfs1
            if ((data[1] < data[0]) && (stack.isEmpty() || stack.peekFirst() <= index)) {
                prefix.add(datas[index * 2]);
                stack.offerFirst(index);
                data[1]++;
                dfs2(res, temp, prefix, stack, len);

                data[1]--;
                prefix.remove(prefix.size() - 1);
                stack.pollFirst();
            }

            if (data[2] < data[0] && data[2] < data[1] && stack.peekFirst() == index) {
                prefix.add(datas[index * 2 + 1]);
                int needRestor = stack.pollFirst();
                data[2]++;
                dfs2(res, temp, prefix, stack, len);

                data[2]--;
                prefix.remove(prefix.size() - 1);
                // don't forget to offer, let the state of stack to be not changed
                stack.offerFirst(needRestor);
            }
            index++;
        }

    }

    public static void main(String[] args) {
        AllValidPermutationsOfParentheses2 allValidPermutationsOfParentheses2 = new AllValidPermutationsOfParentheses2();
        System.out.println(allValidPermutationsOfParentheses2.allParentheses(1, 1, 1));
    }
}
