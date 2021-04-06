package january2021.week2.minimumopertationtoreducextozero;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 * <p>
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * Example 2:
 * <p>
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 */
class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int target = sum - x;

        if (target == 0) return nums.length; // remove all

        int left = 0, right = 0, len = 0;
        sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }
            if (sum == target) {
                len = Integer.max(len, right - left + 1);
            }
            right++;
        }
        if (len == 0) return -1;
        return nums.length - len;
    }
}
