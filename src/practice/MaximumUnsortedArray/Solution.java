package practice.MaximumUnsortedArray;

import java.util.Arrays;

class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Integer.min(min, nums[i]);
            }
        }
        if (!flag) {
            return 0;
        }

        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Integer.max(max, nums[i]);
            }
        }

        int left = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > min) {
                left = Integer.min(left, i);
            }
        }

        int right = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < max) {
                right = Integer.max(right, i);
            }
        }

        return right - left + 1;

    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(new Solution().findUnsortedSubarray(nums));
    }
}
