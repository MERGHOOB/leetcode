package leetcode_top_k_frequent_words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.count - a.count);

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        //Instead of priioryt queue- we can do better
//        for (int key : countMap.keySet()) {
//            pq.add(new Node(key, countMap.get(key)));
//        }
//
//        for (int i = 0; i < k && i < pq.size(); i++) {
//            result[i] = pq.poll().value;
//        }
        //
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : countMap.keySet()) {
            int frequency = countMap.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }


        Iterator<Integer> iterator = result.iterator();
        int j = 0;
        while (iterator.hasNext() && j < k) {
            res[j++] = iterator.next();
        }
        return res;
    }

    static class Node {
        int value;
        int count;

        Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
