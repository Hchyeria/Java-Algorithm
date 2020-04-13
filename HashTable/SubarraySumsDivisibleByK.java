package HashTable;

import sun.java2d.ScreenUpdateManager;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] A, int K){
        long prefix = 0L;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        int count = 0;
        for (int i : A) {
            // when prefix < 0
            prefix += i;
            prefix = ((prefix % K) + K) % K;
            Integer c = map.get(prefix);
            if (c != null) {
                count += c;
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySumsDivisibleByK subarraySumsDivisibleByK = new SubarraySumsDivisibleByK();
        int[] a = {4,5,0,-2,-3,1};
        System.out.println(subarraySumsDivisibleByK.subarraysDivByK(a, 5));
    }
}
