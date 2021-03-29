package weeklychallenge.week1.groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lower-case English letters.
 */
class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashedGroup = new HashMap<>();
        for (String str : strs) {
            String hash = getHash(str);
            hashedGroup.putIfAbsent(hash, new ArrayList<>());
            hashedGroup.get(hash).add(str);
        }

        return new ArrayList<>(hashedGroup.values());
    }

    private String getHash(String str) {
        int[] count = new int[26];
        Arrays.fill(count, 0);
        for (char character : str.toCharArray()) {
            count[character - 'a']++;
        }
        StringJoiner stringJoiner = new StringJoiner("#");
        for (int j : count) {
            stringJoiner.add(String.valueOf(j));
        }
        return stringJoiner.toString();
    }


}
