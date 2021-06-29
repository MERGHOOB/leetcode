package maximumlengthofpairchain;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int findLongestChainUsingGreedy(int[][] pairs) {

        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));

        int len = 0;int curr = Integer.MIN_VALUE;
        for(int[] pair: pairs) {
            if(curr < pair[0]) {
                curr = pair[1];
                len++;
            }
        }

     return len;
    }

    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int len = 1;

        for (int i = 1; i < pairs.length; i++) {

            for (int j = i - 1; j >= 0; j--) {

                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }


            }
        }

        for (int i = 0; i < dp.length; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;
    }

}
