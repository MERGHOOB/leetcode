package leetcode_lonegest_word_in_dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public String longestWord(String[] words) {

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i + 1);
        }

        trie.words = words;
        return trie.dfs();
    }

    private static class Trie {
        public String[] words;
        Node root = new Node('0', 0);

        public void insert(String word, int index) {
            Node curr = root;
            for (Character character : word.toCharArray()) {
                if (curr.children.containsKey(character)) {
                    curr = curr.children.get(character);
                } else {
                    curr.children.put(character, new Node(character));
                    curr = curr.children.get(character);
                }
            }
            curr.end = index;
        }

        public String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack<>();

            stack.push(root);
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                if (pop.end >= 0 || pop == root) {
                    if (pop != root) {
                        String word = words[pop.end - 1];
                        if (word.length() > ans.length()) {
                            ans = word;
                        } else if (word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (Node node : pop.children.values()) {
                        stack.push(node);
                    }
                }
            }

            return ans;
        }
    }

    private static class Node {

        Character character;
        int end;
        Map<Character, Node> children = new HashMap<>();


        public Node(Character character, int index) {
            this.character = character;
            this.end = index;
        }

        public Node(Character character) {
            this.character = character;
        }
    }
}