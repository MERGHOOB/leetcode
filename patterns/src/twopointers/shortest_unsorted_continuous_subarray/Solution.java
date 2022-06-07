package twopointers.shortest_unsorted_continuous_subarray;

/**
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int left = 0;

        while(left + 1 < nums.length) {
            if(nums[left] <= nums[left+1]) {
                left++;
            }
            else {
                break;
            }
        }

        // left = 1

        if(left == nums.length-1) {
            return 0;
        }

        int right = nums.length-1;
        while(right -1 > 0) {
            if(nums[right] >= nums[right-1]) {
                right --;
            }
            else {
                break;
            }
        }
        // right = 3


        // get the min and max of nums[left, right]

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = left; i<=right; i++) {
            min = Math.min(min, nums[i]);
            max= Math.max(max, nums[i]);
        }

        // to update left from nums[0... left-1];if any ele is bigger than min than that will be the index;
        //
        // min = 2
        // max = 3;

        for(int i = 0; i<left; i++) {
            if(nums[i] > min) {
                left = i;
                break;
            }
        }

        for(int j = nums.length-1; j>right; j--) {
            if(nums[j] < max) {
                right = j;
                break;
            }
        }


        return right - left + 1;
    }


}