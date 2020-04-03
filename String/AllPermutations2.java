package String;

// Given a string with possible duplicate characters, return a list with all permutations
// of the characters.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllPermutations2 {
    public List<String> permutations(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        if (set.length() <= 1) {
            result.add("");
            return result;
        }
        dfs(set.toCharArray(), result, 0);
        return result;
    }

    private void dfs(char[] array, List<String> res, int index) {
        if (index == array.length) {
            res.add(new String(array));
            return;
        }
        HashSet<Character> characters = new HashSet<>();
        for (int i = index; i < array.length; ++i) {
            // will return false if arr[i] is already in the set, so duplicate letter will be pruned
            if (characters.add(array[i])) {
                swap(array, index, i);
                dfs(array, res, index + 1);
                swap(array, index, i);
            }
        }
    }

    private void swap(char[] array, int j, int i) {
        if (i != j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time complexity: O(n + n*(n-1) + n*(n-1)*(n-2) + ... + n!) = O(n!)
    // Space complexity: O(n) + O(n) because of call-stack + .toCharArray()
}
