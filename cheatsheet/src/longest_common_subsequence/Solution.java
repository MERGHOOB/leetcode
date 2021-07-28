package longest_common_subsequence;

class Solution {
    public int longestCommonSubsequence(String first, String second) {


        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int i = 1; i <= first.length(); i++) {

            for (int j = 1; j <= second.length(); j++) {

                dp[i][j] = (first.charAt(i - 1) == second.charAt(j - 1)
                        ? (1 + dp[i - 1][j - 1]) :
                        Math.max(dp[i][j - 1], dp[i - 1][j]));


            }


        }

        return dp[first.length()][second.length()];
    }
}
