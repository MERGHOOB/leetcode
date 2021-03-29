package weeklychallenge.week1.maximumsubarray;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * max = 0;
 * max = 1
 * max = 6
 * curr = 5
 */
class Solution {
    public int maxSubArray(int[] nums) {

//        return maxSubArrayOn(nums);

        return maxSubArray(nums, 0, nums.length-1);
    }

    private int maxSubArray(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }
        int mid = (left + right) /2;

        int leftSum = maxSubArray(nums, left, mid);
        int rightSum = maxSubArray(nums, mid+1, right);
        int maxCrossingSum = maxCrossingSum(nums, left, right, mid);

        return  Integer.max(leftSum, Integer.max(rightSum, maxCrossingSum));
    }

    private int maxCrossingSum(int[] nums, int left, int right, int mid) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = mid; i>=left; i--) {
            sum += nums[i];
            leftSum = Integer.max(leftSum, sum);
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for(int i = mid+1; i<= right; i++) {
            sum += nums[i];
            rightSum = Integer.max(rightSum, sum);
        }

        return Integer.max(leftSum+ rightSum, Integer.max(leftSum, rightSum));

    }

    private int maxSubArrayOn(int[] nums) {
        int max = Integer.MIN_VALUE;
        int currSum = 0;
        for (int num : nums) {
            currSum += num;
            if (currSum < 0) {
                max = Integer.max(currSum, max);
                currSum = 0;
            } else {
                max = Integer.max(currSum, max);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        int nums [] = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Solution().maxSubArray(nums));
    }
}