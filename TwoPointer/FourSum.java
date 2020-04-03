package TwoPointer;

// Determine if there exists a set of four elements in a given array that sum to the
// given target number.

// Assumption: The given array is not null and has length of at least 4.

// Solutions:
// 0. Sort + Three Sum: Time complexity is O(n^3), space complexity is O(log(n)),
//    because of quick sort (for primitive types).
// 1. Construct all pairs + Two Sum (sort + two pointers): Time complexity is
//    O(n^2 * log(n^2)) = O(n^2 * log(n)), space complexity is O(n^2),
//    because of merge sort (for non-primitive types).
// 2. Construct all pairs + Two Sum (with hash map): Time complexity is O(n^2), space
//    complexity is O(n^2).


import java.util.*;

// I cry TwT
public class FourSum {
        private static class Pair {
            int left;
            int lIndex;
            int right;
            int rIndex;
            Pair(int left, int l, int right, int r) {
                this.left = left;
                this.right = right;
                lIndex = l;
                rIndex = r;
            }

            @Override
            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Pair) || getClass() != o.getClass()) {
                    return false;
                }
                Pair pair = (Pair) o;
                return left == pair.left && right == pair.right;
            }

            @Override
            public int hashCode() {
                return Objects.hash(left, right);
            }
        }
        // use the set to deduplicate
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            Set<List<Integer>> resSet = new HashSet<>();
            Map<Integer, Set<Pair>> pairMap = new HashMap<>();
            int n = nums.length;
            for (int j = 0; j < n; ++j) {
                for (int i = 0; i < j; ++i) {
                    int sum = nums[i] + nums[j];
                    Set<Pair> other = pairMap.get(target - sum);

                    if (other != null) {
                        for (Pair pair : other) {
                            if (pair.rIndex < i) {
                                resSet.add(Arrays.asList(pair.left, pair.right, nums[i], nums[j]));
                            }
                        }
                    }
                    pairMap.computeIfAbsent(sum, key -> new HashSet<>())
                            .add(new Pair(nums[i], i, nums[j], j));
                }
            }
            return new ArrayList<>(resSet);
        }

    public boolean exist2(int[] array, int target) {
        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                int sum = array[j] + array[i];
                if (map.containsKey(target - sum) && map.get(target - sum).right < j) {
                    return true;
                }
                // If sum does not exist in map, put it into map, otherwise keep the current
                // one since it has the smaller right index. (?)
                if (!map.containsKey(sum)) {
                    map.put(sum, new Pair(array[i], i, array[j], j));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        System.out.println(fourSum.fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
    }
}
