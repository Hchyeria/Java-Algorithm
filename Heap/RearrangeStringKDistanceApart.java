package Heap;

// Given a non-empty string str and an integer k,
// rearrange the string such that the same characters are at least distance k from each other.
// All input strings are given in lowercase letters.
// If it is not possible to rearrange the string, return an empty string "".
// Example 1: str = "aabbcc", k = 3
// Result: "abcabc"
// The same letters are at least distance 3 from each other. Example 2: str = "aaabc", k = 3
// Answer: ""
// It is not possible to rearrange the string. Example 3: str = "aaadbbcc", k = 2
// Answer: "abacabcd"
// Another possible answer is: "abcabcda"
// The same letters are at least distance 2 from each other.

import java.util.*;

public class RearrangeStringKDistanceApart {

    public String rearrangeString(String str, int k) {
        if (str == null || str.length() <= 1 || k <= 0) {
            return str;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            map.put(index, map.getOrDefault(index, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) ->
                a.getValue().equals(b.getValue()) ? Integer.compare(a.getKey(), b.getKey()) : Integer.compare(b.getValue(), a.getValue()));

        pq.addAll(map.entrySet());
        List<Map.Entry<Integer, Integer>> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            int n = k;
            int i = res.size();
            while (n > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> entry = pq.poll();
                entry.setValue(entry.getValue() - 1);
                res.add(entry);
                n--;
            }

            int right = res.size();
            for (; i < right; ++i) {
                if (res.get(i).getValue() > 0) pq.add(res.get(i));
            }
            if (pq.isEmpty()) break;
            if (n > 0) return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> i : res) {
            sb.append((char) (i.getKey() + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RearrangeStringKDistanceApart rearrangeStringKDistanceApart = new RearrangeStringKDistanceApart();
        System.out.println(rearrangeStringKDistanceApart.rearrangeString("aabbcc", 3));
        System.out.println(rearrangeStringKDistanceApart.rearrangeString("aaabc", 3));
        System.out.println(rearrangeStringKDistanceApart.rearrangeString("aaadbbcc", 2));
    }
}
