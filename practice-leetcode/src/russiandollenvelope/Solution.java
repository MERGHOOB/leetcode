package russiandollenvelope;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });


        int[] result = new int[envelopes.length];
        result[0] = envelopes[0][1]; // width based LIS
        int len = 1;
        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][1] > result[len - 1]) {
                result[len++] = envelopes[i][1];
            } else {
                int replaceIndex = Arrays.binarySearch(result, 0, len - 1, envelopes[i][1]);
                if (replaceIndex < 0) {
                    replaceIndex = -(replaceIndex + 1);
                }
                result[replaceIndex] = envelopes[i][1];
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int[][] nums = {{2, 1}, {4, 1}, {6, 2}, {8, 3}, {10, 5}, {12, 8}, {14, 13}, {16, 21}, {18, 34}, {20, 55}};
        System.out.println(new Solution().maxEnvelopes(nums));
    }
}