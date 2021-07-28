package leetcode_word_ladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */
/*
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]

observation: hit is not included in wordList, it will be better if i add this into wordlist.
bfs-->  is a fast way to find shortest path in undirected graph

if hit is the node --> neighbours(hit) ==> hot,
        neighbour of hot ==> dot, lot
        one char change==> on same postion ==> hot ==> toy is not a neighbour

 */
public class Solution {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> dictionary = new HashSet<>(wordList);

        // two way bfs, will be fast

        Set<String> top = new HashSet<>();
        top.add(beginWord);
        Set<String> down = new HashSet<>();
        down.add(endWord);
        int level = 1;
        Set<String> visited = new HashSet<>();

        while (!top.isEmpty()) {

            if (down.size() > top.size()) {
                Set<String> temp = top;
                top = down;
                down = temp;
            }

            Set<String> neighbours = new HashSet<>();
            for (String curr : top) {
                visited.add(curr);
                for (String neighbour : getNeighbours(curr, dictionary)) {
                    if (visited.contains(neighbour)) {
                        continue;
                    }
                    if (down.contains(neighbour)) {
                        return level + 1;
                    }
                    neighbours.add(neighbour);
                }
            }
            top = neighbours; // as top is finished so we will update top with neighbours
            level += 1;
        }
        return 0;
    }

    private List<String> getNeighbours(String curr, Set<String> dictionary) {

        List<String> neighbours = new ArrayList<>();
        char[] chars = curr.toCharArray();
        for (int i = 0; i < curr.length(); i++) {
            char c = chars[i];
            for (char temp = 'a'; temp <= 'z'; temp++) {
                if (temp == c) {
                    continue;
                }
                chars[i] = temp;
                String word = new String(chars);
                if (dictionary.contains(word)) {
                    neighbours.add(word);
                }
            }
            chars[i] = c;
        }

        return neighbours;
    }


}

