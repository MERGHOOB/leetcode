package weeklychallenge.week4.longestcommonsubsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English character
 */
class Solution {
    private Map<String, Integer> map = new HashMap<>();
    public int longestCommonSubsequence(String text1, String text2) {
       if(text1.isEmpty() || text2.isEmpty()) {
           return 0;
       }
        int[][] dp = new int[text1.length()][text2.length()];
        for(int i = 0; i<text1.length(); i++) {
            for(int j = 0; j<text2.length(); j++) {

                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + ((i >= 1 && j >= 1) ? dp[i - 1][j - 1] : 0);
                }
                else {
                    int top = i>=1? dp[i - 1][j]: 0;
                    int left = j>=1? dp[i][j-1]: 0;
                    dp[i][j] = Integer.max(top,left);
                }

            }
        }
        return dp[text1.length()-1][text2.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonSubsequence("abcde", "ace"));
    }
}