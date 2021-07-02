package distinct_subsequence;

/*
https://leetcode.com/problems/distinct-subsequences/
 */
public class Solution {

    public int numDistinct(String s, String t) {

        if (t.length() >= s.length()) {
            return 0;
        }


        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[0][i] = 1;// empty sequence
        }

        for (int i = 1; i <= t.length(); i++) {

            for (int j = 1; j <= s.length(); j++) {

                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                dp[i][j] += dp[i][j - 1]; // if char matches, still we want the target to be matched with rest of characters of s

            }
        }

        return dp[t.length()][s.length()];
    }
}
