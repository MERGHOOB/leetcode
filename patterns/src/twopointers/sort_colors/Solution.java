package twopointers.sort_colors;
/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

// one way to calculate zeros, ones and twos and fill accordingly;
two iteration;

Following is just one iteration
 */

class Solution {
    public void sortColors(int[] nums) {

        int zero = 0, two = nums.length - 1;
        int iterator = 0;
        while (iterator <= two) {
            if (nums[iterator] == 0) {
                swap(nums, zero, iterator);
                zero++;
                iterator++;
            } else if (nums[iterator] == 1) {
                iterator++;
            } else {
                swap(nums, two, iterator);
                two--;
            }
        }

    }

    void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}

