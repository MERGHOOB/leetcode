package weeklychallenge.week3.productofarrayexceptself;

import java.util.Arrays;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Follow up:

Could you solve it in O(n) time complexity and without using division?
====
create to array LeftProuct and rightProduct
for input: [1,2,3,4]
leftProduct= 1, 2, 6, 24
lightProduct= 24,24, 12, 4

for each index i; rightPrefixProduct = rightProduct[i+1] or i+1 out of index: 1;
for each index i; leftPrefixProduct = leftProduct[i-1] or i-1 out of index: 1;
===

Could you solve it with O(1) constant space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
class Solution {
    public int[] productExceptSelfWithSpace(int[] nums) {

        int[] leftProduct = new int[nums.length];
        Arrays.fill(leftProduct, 1);

        int[] rightProduct = new int[nums.length];
        Arrays.fill(rightProduct, 1);

        for (int i = 0; i < nums.length; i++) {
            leftProduct[i] = (i == 0 ? 1 : leftProduct[i - 1]) * nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            rightProduct[i] = (i == nums.length - 1 ? 1 : rightProduct[i + 1]) * nums[i];

        }
        int[] output = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int leftPrefixProduct = i == 0 ? 1 : leftProduct[i - 1];
            int rightPrefixProduct = i == nums.length - 1 ? 1 : rightProduct[i + 1];
            output[i] = leftPrefixProduct * rightPrefixProduct;
        }
        return output;
    }
    public int[] productExceptSelf(int[] nums) {

        int[] answer = new int[nums.length];
        Arrays.fill(answer, 1);

        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i-1];
        }
        int rightPrefixProduct = 1; // for last element it will be 1
        for(int i = nums.length-1; i>= 0; i--) {
            int leftPrefixProduct = i==0? 1: answer[i];
            answer[i] = leftPrefixProduct* rightPrefixProduct;
            rightPrefixProduct = rightPrefixProduct* nums[i];
        }


       return answer;
    }
}
