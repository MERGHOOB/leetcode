package leetcode_longest_substring_without_repeating_characters;

import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()) {
            return 0;
        }
//        int[] chars = new int[256];
        int[] pos = new int[256];
        Arrays.fill(pos, -1);

        int i = -1, j = 0, maxLen = -1;
        while (j < s.length()) {

            if (pos[s.charAt(j)] == -1) {
                pos[s.charAt(j)] = j;
                maxLen = Math.max(maxLen, j - i);
            } else {
                int index = pos[s.charAt(j)];
                while (++i < index) {
                    pos[s.charAt(i)] = -1;
                }
                pos[s.charAt(j)] = j;
            }
            j++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abba"));
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new Solution().lengthOfLongestSubstring("a"));
        System.out.println(new Solution().lengthOfLongestSubstring("bababa"));
        System.out.println(new Solution().lengthOfLongestSubstring("bbbb"));
    }
}