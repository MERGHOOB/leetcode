package twopointers.squaring_a_sorted_array;

/*
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
 */
class Solution {
    public int[] sortedSquares(int[] nums) {

        int[] res = new int[nums.length];

        int i = 0, j = nums.length - 1;

        int end = j;

        while (i <= j) {

            int absI = Math.abs(nums[i]);
            int absJ = Math.abs(nums[j]);

            if (absI > absJ) {
                res[end] = absI * absI;
                i++;
            }
            else {
                res[end] = absJ * absJ;
                j--;
            }
            end--;

        }

        return res;

    }
}