package leetcode_word_ladder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionUsingDeque {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<String> queue = new LinkedList<>(); // for adding at last and receiving from first

        queue.add(beginWord);
        int level = 1;

        boolean[] visited = new boolean[wordList.size()];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                String temp = queue.poll();
                if (temp.equals(endWord)) {
                    return level;
                }

                for (int j = 0; j < wordList.size(); j++) {
                    if (!visited[j]
                            && !temp.equals(wordList.get(j))
                            && check(temp, wordList.get(j))) {
                        queue.add(wordList.get(j));
                        visited[j] = true;
                    }
                }
            }

            level += 1;
        }
        return level;
    }

    private boolean check(String first, String second) {
        int result = 0;
        for (int i = 0; i < first.length(); i++) {
            char c1 = first.charAt(i);
            char c2 = second.charAt(i);
            if (c1 != c2) {
                result += 1;
            }
        }
        return result == 1; // diff should be only 1
    }
}