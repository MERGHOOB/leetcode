package minimum_ascii_delete_sum_for_two_strings;

/*
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 */
class Solution {
    public int minimumDeleteSumOptimizedSpace(String first, String second) {

        //base case dp[0][0] = 0; both are empty
        // target: dp[m][n];

        int[] dp = new int[second.length() + 1];

        for (int j = 1; j <= second.length(); j++) {
            dp[j] = dp[j - 1] + second.codePointAt(j - 1);
        }


        int t1, t2;
        for (int i = 1; i <= first.length(); i++) {
            t1 = dp[0];
            dp[0] += first.charAt(i - 1);
            for (int j = 1; j <= second.length(); j++) {

                t2 = dp[j];
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[j] = t1;
                } else {
                    dp[j] = Math.min(dp[j] + first.codePointAt(i - 1), dp[j - 1] + second.codePointAt(j - 1));
                }
                t1 = t2;
            }
        }
        return dp[second.length()];
    }

    public int minimumDeleteSum(String first, String second) {

        //base case dp[0][0] = 0; both are empty
        // target: dp[m][n];

        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int j = 1; j <= second.length(); j++) {
            dp[0][j] = dp[0][j - 1] + second.codePointAt(j - 1);
        }

        for (int i = 1; i <= first.length(); i++) {
            dp[i][0] = dp[i - 1][0] + first.codePointAt(i - 1);
        }

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {

                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + first.codePointAt(i - 1), dp[i][j - 1] + second.codePointAt(j - 1));
                }
            }
        }
        return dp[first.length()][second.length()];
    }
}
