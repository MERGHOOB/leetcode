package edit_distance;

/*
https://leetcode.com/problems/edit-distance/
 */
class Solution {
    public int minDistance(String word1, String word2) {

        if (word1.isEmpty()) {
            return word2.length();
        } else if (word2.isEmpty()) {
            return word1.length();
        }

        int[][] dp = new int[word1.length() + 1][word2.length()+1];

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}