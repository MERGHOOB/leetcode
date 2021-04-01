package january2021.week1.longestsubstringwithoutrepeatingcharacters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
//        return lengthOfLongestSubstringBruteForce(s);
        return lengthOfLongestSubstringSlidingWindow(s);
    }

    private int lengthOfLongestSubstringSlidingWindow(String string) {

        int max = 0;
        char[] chars = string.toCharArray();
        int first = 0;
        int second;
        int[] index = new int[256]; // it will work as map : character to last index seen; if not seen yet: -1;
        Arrays.fill(index, -1);

        for (second = 0; second < chars.length; second++) {
            if (index[chars[second]] !=-1) {
                first = Integer.max(first, index[chars[second]]+1);
            }
            index[chars[second]]= second;
            max = Integer.max(max, second - first + 1);
        }
        return max;
    }

    private int lengthOfLongestSubstringBruteForce(String string) {
        int n = string.length();
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substr = string.substring(i, j);
                if (allCharacterUnique(substr)) {
                    max = Integer.max(substr.length(), max);
                }
            }
        }
        return max;
    }

    private boolean allCharacterUnique(String substr) {
        Map<Character, Integer> map = new HashMap<>();

        char[] chars = substr.toCharArray();
        for (char ch : chars) {
            if (map.containsKey(ch)) {
                return false;
            }
            map.put(ch, 1);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringSlidingWindow("dvdf"));
    }
}