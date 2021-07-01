package partition_equal_subset_sum;

/*
https://leetcode.com/problems/partition-equal-subset-sum/
 */
class Solution {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        int dpRow = nums.length + 1;
        boolean[][] dp = new boolean[dpRow][target + 1];

        for (int i = 1; i <= target; i++) {
            dp[0][i] = false;
        }
        for (int i = 0; i < dpRow; i++) {
            dp[i][0] = true;   // dp[0][0] will become true here
        }

        for (int i = 1; i < dpRow; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] ||
                        j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]];
            }
        }

//        for ( int i = 0; i<dpRow; i++) {
//            for(int j = 0; j<=target; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        return dp[dpRow-1][target];
    }
    public boolean canPartitionWithSumSpace(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        int dpRow = nums.length + 1;
        boolean [] row = new boolean[target + 1];
        boolean [] temp = new boolean[target + 1];

        row[0] = true;

        for (int i = 1; i < dpRow; i++) {
            for (int j = 1; j <= target; j++) {
                temp[j] = row[j];
                if (j >= nums[i - 1]) {
                    temp[j] = row[j] || row[j - nums[i - 1]];
                } else {
                    temp[j] = row[j];
                }
            }
            row = temp;
        }

//        for ( int i = 0; i<dpRow; i++) {
//            for(int j = 0; j<=target; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        return row[target];
    }

    public static void main(String[] args) {
        int [] nums = {1};
        System.out.println(new Solution().canPartitionWithSumSpace(nums));
    }
}