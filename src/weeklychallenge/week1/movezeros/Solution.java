package weeklychallenge.week1.movezeros;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 */
class Solution {


    public void moveZeroes(int[] nums) {

        int firstZeroIndex = findFirstZeroIndex(nums, 0);
        if (firstZeroIndex == -1) {
            return;
        }

        for (int i = firstZeroIndex + 1; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[firstZeroIndex] = nums[i];
                nums[i] = 0;
                firstZeroIndex = findFirstZeroIndex(nums, firstZeroIndex + 1);
                if (firstZeroIndex == -1) {
                    return;
                }
                i = firstZeroIndex;
            }


        }

    }

    private int findFirstZeroIndex(int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {0, 1, 1, 0};
        new Solution().moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println(Arrays.asList(nums));
    }
}
