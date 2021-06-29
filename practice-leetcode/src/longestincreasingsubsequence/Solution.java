package longestincreasingsubsequence;

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        int len = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > result[len - 1]) {
                result[len++] = nums[i];
            } else {
                int findIndex = Arrays.binarySearch(result, 0, len - 1, nums[i]);
                if (findIndex < 0) {
                    findIndex = -(findIndex + 1); //binary search return -(low+1); and we need to find low

                }
                result[findIndex] = nums[i];
            }
        }
        return len;

    }


    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = {7,7};
        System.out.println(new Solution().lengthOfLIS(nums));
    }

}