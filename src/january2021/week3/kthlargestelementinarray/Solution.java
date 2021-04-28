package january2021.week3.kthlargestelementinarray;

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {

//        int prSize = nums.length -k ;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {

            if (priorityQueue.size() < k) {
                priorityQueue.add(num);
                continue;
            }
            priorityQueue.add(num);
            priorityQueue.poll();

        }

        return priorityQueue.poll();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(new Solution().findKthLargest(nums, k));
    }
}
