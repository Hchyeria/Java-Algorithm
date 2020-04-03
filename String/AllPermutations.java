package String;

// Given a string with no duplicate characters, return a list with all permutations
// of the characters.


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPermutations {
    public List<String> permutations(String set) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        if (set.length() == 0) {
            res.add("");
            return res;
        }
        dfs(set.toCharArray(), res, 0);
        return res;
    }

    private void dfs(char[] set, List<String> res, int index) {
        if (index == set.length) {
            res.add(new String(set));
            return;
        }
        for (int i = index; i < set.length; ++i) {
            swap(set, index, i);
            dfs(set, res, index + 1);
            swap(set, index, i);
        }
    }

    private void swap(char[] set, int i, int j) {
        if (i != j) {
            char temp = set[i];
            set[i] = set[j];
            set[j] = temp;
        }
    }

    // Time complexity: O(n + n*(n-1) + n*(n-1)*(n-2) + ... + n!) = O(n!)
    // Space complexity: O(n) + O(n) because of call-stack + .toCharArray()
}
