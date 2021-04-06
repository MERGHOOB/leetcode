package january2021.week2.wordladder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
class Solution {
    int miniumtrasnformationRequired = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

      if(endWord.equals(beginWord) || !wordList.contains(endWord)) {
          return 0;
      }

//        return uniDirectionBFS(beginWord, endWord, wordList); TLE
        return biDirectionalBFS(beginWord, endWord, wordList);
    }

    private int biDirectionalBFS(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        Set<String> start = new HashSet<>();start.add(beginWord);
        Set<String> end = new HashSet<>(); end.add(endWord);

        Set<String> visited = new HashSet<>();
        int level = 1;
        while(!start.isEmpty()) {
            if(start.size() > end.size()) {
                Set<String> temp = start; start = end; end = temp;
            }

            Set<String> neighbours = new HashSet<>();
            for(String curr: start) {
                visited.add(curr);
                for(String next: getNext(curr, wordSet)) {
                    if(visited.contains(next)) {
                        continue;
                    }
                    if(end.contains(next)) {
                        return level + 1;
                    }
                    neighbours.add(next);
                }
            }
            start = neighbours;
            level ++;
        }
        return 0;
    }

    private List<String> getNext(String curr, Set<String> wordSet) {
        List<String> list = new ArrayList<>();
        char[] chars = curr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == c) {continue;}
                chars[i] = j;
                String candidate = new String(chars);
                if (wordSet.contains(candidate)) {
                    list.add(candidate);
                }
                chars[i] = c;
            }
        }
        return list;
    }

    private int uniDirectionBFS(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {

            int size = queue.size();
            for(int i = 0; i<size; i++) {
                String word = queue.remove();
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char ch = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (ch == c) {
                            continue;
                        }
                        chars[j] = c;
                        String candidate = String.valueOf(chars);
                        if (candidate.equals(endWord)) return level + 1;
                        if (wordList.contains(candidate)) {
                            queue.add(candidate);
                            wordList.remove(candidate);
                        }
                    }
                    chars[j] = ch;
                }
            }
            level++;
        }
        return 0;
    }


    private boolean isTargetAccessible(String key, String target) {
        if (key.length() != target.length()) {
            return false;
        }
        int[] keyCharCount = new int[128];
        Arrays.fill(keyCharCount, 0);
        char[] keyArray = key.toCharArray();
        for (char ch : keyArray) {
            keyCharCount[ch]++;
        }
        char[] targetArray = target.toCharArray();
        for (char ch : targetArray) {
            keyCharCount[ch]--;
        }

        int count = 0;
        for (int j : keyCharCount) {
            if (j != 0) {
                count++;
                if (count > 2) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> strings = Arrays.asList(wordList);
        int i = new Solution().ladderLength(beginWord, endWord, strings);
        System.out.println(i);
    }
}