package leetcode_find_K_pairs_with_smallest_sums;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;


        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);

        for (int element : nums1) {   // O(m)
            pq.offer(new int[]{element, nums2[0], 0});
        }


        while (k-- != 0 && !pq.isEmpty()) {   // O(k)

            int[] poll = pq.poll();
            res.add(List.of(poll[0], poll[1]));

            if ((poll[2] + 1) < nums2.length) {
                pq.offer(new int[]{poll[0], nums2[poll[2] + 1], poll[2] + 1});
            }

        }

        return res;

    }
}