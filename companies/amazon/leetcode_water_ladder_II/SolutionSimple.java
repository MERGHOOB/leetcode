package leetcode_water_ladder_II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class SolutionSimple {


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();


        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(beginWord)); // add the begin node

        while (!queue.isEmpty()) {

            int size = queue.size();
            // tempSet to remove all the used word after the iteration
            Set<String> removeSet = new HashSet<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr.name.equals(endWord)) {
                    //path found
                    result.add(curr.path);
                } else {
                    List<String> neighbours = getNeighbours(curr.name, wordSet);
                    for (String neighbour : neighbours) {
                        queue.add(new Node(neighbour, curr.path));
                        removeSet.add(neighbour);// add the words getting used, later we will delete all.
                    }
                }
            }
            wordSet.removeAll(removeSet);
        }

        return result;
    }

    private List<String> getNeighbours(String name, Set<String> wordSet) {
        char[] chars = name.toCharArray();
        List<String> words = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String candidate = new String(chars);
                if (wordSet.contains(candidate)) words.add(candidate);
            }
            chars[i] = temp;
        }
        return words;
    }

    private static class Node {
        String name;
        LinkedList<String> path;

        public Node(String name) {
            this.name = name;
            path = new LinkedList<>();
            this.path.add(name);
        }

        // add the history of parent paths and to itself

        public Node(String name, LinkedList<String> parentPath) {
            this.name = name;
            this.path = new LinkedList<>();
            path.addAll(parentPath);
            path.add(name);
        }
    }
}