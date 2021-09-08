package leetcode_Reorganize_String;

import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString("aaaaaaab"));
        System.out.println(new Solution().reorganizeString("vvvlo"));
    }

    public String reorganizeString(String s) {

        char[] ch = s.toCharArray();

        int len = ch.length;

        int[] freq = new int[26];

        for (char c : ch) {

            freq[c - 'a']++;
        }


        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.count, o1.count));

        for (int i = 0; i < 26; i++) {

            if (freq[i] != 0) {
                pq.add(new Node((char) (i + 'a'), freq[i]));
            }
        }

        char[] result = new char[len];

        Node prev = null;
        int curr = 0;
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            result[curr++] = poll.ch;
            poll.count -= 1;
            if (prev != null && prev.count != 0) {
                pq.add(prev);
            }
            prev = poll;
        }

        return curr == len ? new String(result) : "";

    }

    static class Node {
        char ch;
        int count = 0;

        Node(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

    }
}