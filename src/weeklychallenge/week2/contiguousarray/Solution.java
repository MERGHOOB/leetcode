package weeklychallenge.week2.contiguousarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 */
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, -1);//at -1 index with 0 count;
        int maxLen = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 0 ? 1 : -1);

            if (!countMap.containsKey(count)) {
                countMap.put(count, i);
            } else {
                maxLen = Integer.max(maxLen, i - countMap.get(count));
            }

        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0,1};
        System.out.println(new Solution().findMaxLength(nums));
    }

}
