package weeklychallenge.week1.singleNumber;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 */
class Solution {
    //Best solution no usage of extra memory
    public int singleNumber(int[] nums) {

        // XOR all elements
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        return x;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 1, 2, 2};
        System.out.println(new Solution().singleNumber(nums));
    }
}
