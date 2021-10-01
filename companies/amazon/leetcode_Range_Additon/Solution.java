package leetcode_Range_Additon;

public class Solution {

    /**
     * @param length:  the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here

        int[] res = new int[length];

        for (int[] update : updates) {

            int st = update[0];
            int end = update[1] + 1;

            res[st] += update[2];

            if (end < length) {
                res[end] -= update[2];
            }

        }

        for (int i = 1; i < length; i++) {

            res[i] += res[i - 1];
        }


        return res;
    }

}
