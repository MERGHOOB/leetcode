package leetcoe_word_search_II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public static final int MaximumLowercaseEnglishLatter = 26;

    public List<String> findWords(char[][] board, String[] words) {

        Trie trie = new Trie(words);
        Set<String> foundWords = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (trie.root.isPresent(board[i][j])) {
                    dfs(board, foundWords, trie.root, visited, i, j, "");
                }
            }
        }

        return new ArrayList<>(foundWords);

    }

    private void dfs(char[][] board, Set<String> foundWords,
                     TrieNode root, boolean[][] visited, int i, int j, String currWord) {

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j]) {
            return;
        }

        if (!root.isPresent(board[i][j])) {
            return;
        }
        currWord = currWord + board[i][j];

        TrieNode root1 = root.get(board[i][j]);
        if (root1.isEnd) {
            foundWords.add(currWord);
        }

        visited[i][j] = true;

        dfs(board, foundWords, root1, visited, i + 1, j, currWord);
        dfs(board, foundWords, root1, visited, i - 1, j, currWord);

        dfs(board, foundWords, root1, visited, i, j + 1, currWord);
        dfs(board, foundWords, root1, visited, i, j - 1, currWord);

        visited[i][j] = false;

    }

    private static class Trie {
        TrieNode root = new TrieNode();

        public Trie(String[] words) {
            for (String word : words) {
                build(word);
            }
        }

        private void build(String word) {

            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.isPresent(c)) {
                    node.set(c);
                }
                node = node.get(c);
            }
            node.isEnd = true;
        }


    }

    private static class TrieNode {

        TrieNode[] children = new TrieNode[MaximumLowercaseEnglishLatter];
        boolean isEnd = false;

        public boolean isPresent(Character c) {
            return get(c) != null;
        }

        private TrieNode get(Character c) {
            return children[c - 'a'];
        }

        public void set(char c) {
            children[c - 'a'] = new TrieNode();
        }
    }
}
