package leetcode_longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {


        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();

        int lastDupIndex = -1;

        int max = 0;


        for (int i = 0; i < chars.length; i++) {

            Integer lastIndex = map.get(chars[i]);

            if (lastIndex != null && lastIndex > lastDupIndex) {
                lastDupIndex = lastIndex;
            }

            max = Math.max(max, i - lastDupIndex);
            map.put(chars[i], i);


        }

        return max;
    }
}