package palindromic_substrings;

class Solution {
    public int countSubstrings(String s) {
//        return countSubstringsUsingDP(s);

        return countSubstringsUsingExpandAroundCenter(s);

    }

    private int countSubstringsUsingExpandAroundCenter(String s) {
        int count = 0;
        int n = s.length();
        int centers = (n << 1) + 1;
        for (int center = 0; center < centers; center++) {

            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                count++;
            }

        }
        return count;
    }

    private int countSubstringsUsingDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    count++;
                }
            }

        }
        return count;
    }
}