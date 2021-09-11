package leetcode_add_and_search_word_data_structure_design;

class WordDictionary {

    TrieNode head;

    public WordDictionary() {
        head = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode root = head;

        for (char c : word.toCharArray()) {

            if (!root.isPresent(c)) {
                root.set(c);
            }
            root = root.get(c);
        }
        root.isEnd = true;

    }

    public boolean search(String word) {

        return search(word, 0, head);
    }

    boolean search(String word, int index, TrieNode root) {
        if (index == word.length()) {
            return root.isEnd;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.get(i) != null && search(word, index + 1, root.get(i))) {
                    return true;
                }
            }
        } else {
            if (!root.isPresent(c)) {
                return false;
            }
            return search(word, index + 1, root.get(c));
        }
        return false;
    }


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;

        boolean isPresent(char c) {
            return get(c) != null;
        }

        TrieNode get(char c) {
            return children[c - 'a'];
        }

        TrieNode get(int i) {
            return children[i];
        }

        void set(char c) {
            children[c - 'a'] = new TrieNode();
        }
    }


}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */