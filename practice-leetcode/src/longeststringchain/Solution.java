package longeststringchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
https://leetcode.com/problems/longest-string-chain/submissions/
 */
class Solution {

    public int longestStrChain(String[] words) {
        return similarToLIS(words);
    }

    public int longestStrChainUsingDFS(String[] words) {

        Map<Integer, List<String>> lenMap = new HashMap<>();

        for (String w : words) {
            lenMap.putIfAbsent(w.length(), new ArrayList<>());
            lenMap.get(w.length()).add(w);
        }

        int result = 1;
        Map<String, Integer> cache = new HashMap<>();
        for (String w : words) {
            result = Math.max(result, dfs(w, lenMap, cache) + 1);
        }
        return result;
    }

    private int dfs(String word, Map<Integer, List<String>> lenMap, Map<String, Integer> cache) {
        if (cache.containsKey(word)) {
            return cache.get(word);
        }

        int targetLen = word.length() + 1;
        if (!lenMap.containsKey(targetLen)) {
            return 0;
        }

        int result = 0;
        for (String target : lenMap.get(targetLen)) {
            if (isValidAddition(target, word)) {
                result = Math.max(result, dfs(target, lenMap, cache) + 1);
            }
        }
        cache.put(word, result);
        return result;
    }


    private int similarToLIS(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int[] len = new int[words.length];
        Arrays.fill(len, 1);
        int max = 1;
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isValidAddition(words[i], words[j])) {
                    len[i] = Math.max(len[j] + 1, len[i]);
                }
            }

            max = Math.max(len[i], max);
        }

        return max;
    }

    private boolean isValidAddition(String first, String second) {
        if (first.length() != second.length() + 1) {
            return false;
        }
        boolean skippedOnce = false;
        int j = 0;
        for (int i = 0; i < second.length(); i++) { // second.length as we don't want to

            if (first.charAt(i) != second.charAt(j)) {
                if (skippedOnce) {
                    return false;
                }
                skippedOnce = true;
                continue;
            }
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "ba", "bca", "bda", "bdca"};
        int i = new Solution().longestStrChain(strings);
        System.out.println(i);
    }
}