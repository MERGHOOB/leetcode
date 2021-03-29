package weeklychallenge.week3.validparenthesischeck;

/**
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 * <p>
 * The following rules define a valid string:
 * <p>
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "(*)"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(*))"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s[i] is '(', ')' or '*'.
 * <p>
 * dp[i][j] == s(i) to s(j) is valid string
 * dp[i][j] = dp[i+1][j-1] && s[i] == '(' or '*' and s[j]== ')' or '*'
 */
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') dp[i][i] = true;
            if (i < n - 1 && (s.charAt(i) == '(' || s.charAt(i) == '*')
                    && (s.charAt(i + 1) == ')' || s.charAt(i + 1) == '*')) {
                dp[i][i + 1] = true;
            }
        }
        // now traverse all substring
        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == '*' && dp[i + 1][i + size]) {
                    dp[i][i + size] = true;
                } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    //dp[i][i+size] can be true only if
                    // for some k which can be made ')' such that s[i+1 to k-1] and s[k+1, i+size] is valid
                    for (int k = i + 1; k <= i + size; k++) {
                        if ((s.charAt(k) == ')' || s.charAt(k) == '*')
                                && (k == i + 1 || dp[i + 1][k - 1])
                                && (k == i + size || dp[k + 1][i + size])) {
                            dp[i][i + size] = true;
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkValidString("(*)"));
    }
}