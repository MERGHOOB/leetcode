package lincode_longest_substring_with_atmost_k_unique_chars;

// package lincode_longest_substring_with_atmost_k_unique_chars;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16));
    }

    // public static void main(String[] args) {
    //     System.out.println(new Solution().lengthOfLongestSubstringKDistinct("eceba", 3));
    // }

    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if (k == 0) {
            return 0;
        }
        int len = 0;
        Map<Character, Integer> map = new HashMap<>();

        int left = 0, right = 0, n = s.length();
        while (right < n) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.size() > k) {
                while (left <= right) {
                    ch = s.charAt(left++);
                    if (map.get(ch) == 1) {
                        map.remove(ch);
                        break;
                    } else {
                        map.put(ch, map.get(ch) - 1);
                    }
                }
            } else {
                len = Math.max(len, right - left + 1);
            }


            right++;
        }
        return len;


    }
}
