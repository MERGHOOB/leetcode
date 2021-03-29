package weeklychallenge.week4.jumpgame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 */

enum Index {
    GOOD, BAD, UNKNOWN;
}
class Solution {

    private Map<Integer, Boolean> hashMap = new HashMap<>();
    public boolean canJump(int[] nums) {

        Index [] dp = new Index[nums.length];
        Arrays.fill(dp, Index.UNKNOWN);

        dp[dp.length-1] = Index.GOOD;

        for(int i = nums.length -2; i>=0; i--) {
            int furthestJump = Math.min(i+nums[i], nums.length-1);
            for(int j = i+1; j<=furthestJump; j++) {
                if(dp[j] == Index.GOOD) {
                    dp[i] = Index.GOOD;
                    break;
                }
            }

        }
        return dp[0] == Index.GOOD;
    }

    private boolean canJump(int[] nums, int start, int end) {
        if(start == end) {
            return true;
        }
        if(nums[start] == 0) {
            return false;
        }
        for(int i = nums[start]; i>=1; i--) {
            if(start + i > end) {
                continue;
            }
            Integer key = (start+i);

            hashMap.putIfAbsent(key, canJump(nums, start+i, end));

            if(hashMap.get(key)) {
                return true;
            }
        }
        hashMap.put(start, false);
        return false;
    }
}
