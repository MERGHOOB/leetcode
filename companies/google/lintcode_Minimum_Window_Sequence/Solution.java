package lintcode_Minimum_Window_Sequence;

class Solution {
    /**
     * @param S: a string
     * @param T: a string
     * @return: the minimum substring of S
     */

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("bcbccbdbecdeu", "bde"));
    }

    public String minWindow(String S, String T) {
        // Write your code here

        int start = 0, end = 0;
        int minLen = 20001;
        int n = T.length();
        char[] t = new char[n];
        int i = 0;
        for (char c : T.toCharArray()) {
            t[i++] = c;
        }
        char[] s = S.toCharArray();
        int left = 0, right = 0;
        i = 0;  //
        int nextFirstIndex = -1;
        n = s.length;
        while (right < n) {
            if (s[right] != t[i]) {
                if (s[right] == t[0] && nextFirstIndex == -1) {
                    nextFirstIndex = right;
                }
                if (left == right) {
                    left++;
                    right++;
                } else {
                    right++;
                }
            } else {
                //S[right] = t[i];
                if (i == t.length - 1) {
                    // we found a value
                    if (minLen > right - left + 1) {
                        start = left;
                        end = right;
                        minLen = end - start + 1;
                    }
                    if (nextFirstIndex == -1) {
                        left = right + 1;
                        right = right + 1;
                    } else {
                        left = nextFirstIndex;
                        right = nextFirstIndex;
                        nextFirstIndex = -1;
                    }
                    i = 0;
                } else if (i == 0) { // it means it is first element
                    right++;
                    i++;
                } else {
                    i++;
                    right++;
                }
            }

        }
        return (minLen == 20001) ? "" : S.substring(start, end + 1);
    }
}
