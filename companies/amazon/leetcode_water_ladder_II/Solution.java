package leetcode_water_ladder_II;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    String beginWord;
    String endWord;

    List<List<String>> paths = new ArrayList<>();
    Map<String, List<String>> parentsMap = new HashMap<>();

    private Set<String> dict;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        dict = new HashSet<>(wordList);
        dict.add(beginWord);
        if (!dict.contains(endWord)) return paths;

        this.beginWord = beginWord;
        this.endWord = endWord;

        if (!buildMap()) return paths;

        List<String> curr = new ArrayList<>();
        curr.add(beginWord);

        collectAllPaths(beginWord, curr);

        return paths;
    }

    private void collectAllPaths(String s, List<String> curr) {
        if (s.equals(endWord)) {
            paths.add(new ArrayList<>(curr));
            return;
        }
        for (String next : parentsMap.get(s)) {
            curr.add(next);
            collectAllPaths(next, curr);
            curr.remove(curr.size() - 1);
        }
    }

    private boolean buildMap() {
        Set<String> backward = new HashSet<>();
        backward.add(endWord);

        Set<String> visited = new HashSet<>();
        boolean found = false;

        while (!backward.isEmpty() && !found) {

            Set<String> temp = new HashSet<>();
            for (String s : backward) {
                visited.add(s);

                for (String neighbour : getNeighbours(s)) {
                    if (backward.contains(neighbour) || visited.contains(neighbour)) continue;
                    if (beginWord.equals(neighbour)) found = true;

                    temp.add(neighbour);
                    parentsMap.putIfAbsent(neighbour, new ArrayList<>());
                    parentsMap.get(neighbour).add(s);

                }
            }
            backward = temp;
        }
        return found;
    }

    @SuppressWarnings("DuplicatedCode")
    private List<String> getNeighbours(String name) {
        char[] chars = name.toCharArray();
        List<String> words = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String candidate = new String(chars);
                if (dict.contains(candidate)) words.add(candidate);
            }
            chars[i] = temp;
        }
        return words;
    }
}