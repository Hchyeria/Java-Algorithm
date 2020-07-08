package HashTable;

import java.util.HashMap;
import java.util.Map;

public class TwoSum3 {
    /**
     * @param number: An integer
     * @return: nothing
     */

    private Map<Integer, Integer> map;

    public TwoSum3() {
        map = new HashMap<>();
    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            map.put(k, v - 1);
            Integer i = map.get(value - k);
            if (i != null && i > 0) {
                return true;
            }
            map.put(k, v);
        }

        return false;
    }

    public static void main(String[] args) {
        TwoSum3 twoSum3 = new TwoSum3();
        twoSum3.add(2);
        twoSum3.add(3);
        System.out.println(twoSum3.find(4));
        System.out.println(twoSum3.find(5));
        System.out.println(twoSum3.find(6));
        twoSum3.add(3);
        System.out.println(twoSum3.find(6));
    }
}
