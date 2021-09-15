package leetcode_word_ladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> words) {
        Set<String> wordList = new HashSet<>(words);

        if (!wordList.contains(endWord) || beginWord.equals(endWord)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        int level = 1;
        Set<String> start = new HashSet<>();
        start.add(beginWord);
        Set<String> end = new HashSet<>();
        end.add(endWord);

        while (!start.isEmpty()) {

            if (start.size() > end.size()) {
                Set<String> temp = start;
                start = end;
                end = temp;
            }
            Set<String> neighbours = new HashSet<>();
            Integer minLevel = getLevelOrUpdateNumber(wordList, visited, level, start, end, neighbours);
            if (minLevel != null) return minLevel;
            level++;
            start = neighbours;

        }
        return 0;
    }

    private Integer getLevelOrUpdateNumber(Set<String> wordList,
                                           Set<String> visited,
                                           int level,
                                           Set<String> start,
                                           Set<String> end,
                                           Set<String> neighbours) {
        for (String temp : start) {
            visited.add(temp);
            for (String neighbour : getNeighbours(temp, wordList)) {
                if (visited.contains(neighbour)) {
                    continue;
                }
                if (end.contains(neighbour)) {
                    return level + 1;
                }
                neighbours.add(neighbour);
            }
        }
        return null;
    }

    private List<String> getNeighbours(String temp, Set<String> wordList) {
        List<String> neighbours = new ArrayList<>();

        char[] chars = temp.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == c) continue;
                chars[i] = j;
                String candidate = new String(chars);
                if (wordList.contains(candidate)) neighbours.add(candidate);
                chars[i] = c;
            }
        }
        return neighbours;
    }
}
