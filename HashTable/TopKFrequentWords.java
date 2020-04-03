package HashTable;

// Given a composition with different kinds of words, return a list
// of the top K most frequent words in the composition.

// Assumption:
// 1. The composition is not null and is not guaranteed to be sorted
// 2. K >= 1 and K could be larger than the number of distinct words in the composition,
// 	  in this case, just return all the distinct words

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> hashMap = buildMap(words);
        // define a minHeap
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, (a, b) -> {
            if (a.getValue().equals(b.getValue())) {
                // use .equals(...), == only works for Integer in [-128, 127]
                // because the minHeap, and we should return the alphabetical order
                // so the higher order alphabet should comes before
                // then we reverse the heap, the lower order alphabet will comes before
                return b.getKey().compareTo(a.getKey()); // notice: minHeap -> order of compareTo(...)
            } else {
                return a.getValue().compareTo(b.getValue());
            }
        });
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
           /* if (pq.size() < k) {
                pq.offer(entry);
            } else {
                // notice: there is not only >
                if (pq.peek() != null && entry.getValue() > pq.peek().getValue()
                    || (entry.getValue() == pq.peek().getValue() && entry.getKey().compareTo(pq.peek().getKey()) < 0)) {
                    pq.poll();
                    pq.offer(entry);
                }
            }*/
            // we can use the follow statement to replace the above verbose code
            // add more one element, no need to consider the situation when the key is same
            // don't worry about overflow the initial capacity
            pq.offer(entry );
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> result = new LinkedList<>();
        for (int i = pq.size() - 1; i >= 0; --i) {
            result.add(0, pq.poll().getKey()); // or Collections.reverse(ans);
        }
        return result;

    }

    private Map<String, Integer> buildMap(String[] words) {
        Map<String, Integer> res = new HashMap<>();
        for (String word : words) {
            Integer count = res.getOrDefault(word, 0);
            count++;
            res.put(word, count);
        }
        return res;
    }

    // Time complexity is O(n + n*log(k) + k*log(k)).
    // Space complexity is O(n + k);

    public static void main(String args[]) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();
        String[] word = new String[] {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequentWords.topKFrequent(word, 3));
    }
}
