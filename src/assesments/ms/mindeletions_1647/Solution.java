package assesments.ms.mindeletions_1647;

import java.util.HashSet;

class Solution {

    public int minDeletions(String s) {
        int operation = 0;
        int[] temp = new int[26];
        for (Character character : s.toCharArray()) {
            temp[character - 'a']++;
        }

        HashSet<Integer> freq = new HashSet<>();
        for (int i = 0; i < temp.length; i++) {
            while (temp[i] != 0 && freq.contains(temp[i])) {
                temp[i]--;
                operation++;
            }
            freq.add(temp[i]);
        }
        return operation;
    }

    public static void main(String[] args) {
        String s = "bbcebab";
        int i = new Solution().minDeletions(s);
        System.out.println(i);
    }
}