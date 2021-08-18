package leetcode_kth_largest_element_in_the_array;

import java.util.Random;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;
        int n = nums.length;

        int pivotIndex = -1;
        Random random = new Random(0);

        while (left <= right) {
            int rand = left + random.nextInt(right - left + 1);
            pivotIndex = pivot(nums, left, right, rand);

            if (pivotIndex == (n - k)) {
                return nums[pivotIndex];
            } else if (pivotIndex > n - k) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }


        }
        return -1;


    }

    private int pivot(int[] nums, int left, int right, int p) {
        int pivotValue = nums[p];
        swap(nums, right, p);


        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivotValue) {
                i++;
                swap(nums, i, j);
            }
        }

        // fix the pivot value at correect positon
        swap(nums, right, i + 1);
        return i + 1;
    }


    private void swap(int[] nums, int s, int t) {
        int temp = nums[s];
        nums[s] = nums[t];
        nums[t] = temp;
    }


}