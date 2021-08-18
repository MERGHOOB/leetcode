package leetcode_split_array_largest_sum;

class Solution {
    public int splitArray(int[] nums, int m) {

        int low = 0, high = 0;
        for (int num : nums) {
            low = Math.max(num, low);
            high += num;
        }

        int ans = 0;
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (isPossible(nums, mid, m)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }


    private boolean isPossible(int[] nums, int mid, int m) {

        int sa = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {
                sum = num;
                sa++;
            }
        }

        return sa <= m;  // if equal then we will decrement the mid; which means high = mid -1; OR
        // in less subarrays are used then we need to decrement the mid
        // if more subarrays are used; then we need to increase mid


    }

}