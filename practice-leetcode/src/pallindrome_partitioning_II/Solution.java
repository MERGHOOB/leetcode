package pallindrome_partitioning_II;

/*
https://leetcode.com/problems/palindrome-partitioning-ii/
 */
class Solution {
    public int minCut(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }

        }

        int[] cuts = new int[n];

        for (int i = 0; i < cuts.length; i++) {

            int temp = Integer.MAX_VALUE;
            if (dp[0][i]) {
                cuts[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i] && temp > cuts[j] + 1) {
                        temp = cuts[j] + 1;
                    }
                }
                cuts[i] = temp;
            }
        }
        return cuts[n - 1];
    }
}
