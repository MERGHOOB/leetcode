package leetcode_last_stones_II_special_case_KNAPSACK;

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int total = 0;
        for(int s: stones) total +=s;

        boolean [] dp = new boolean[total/2 + 1];
        dp[0] = true;

        int maxS2 = 0;

        for(int stone: stones) {
            boolean [] temp = dp.clone();

            for(int sum = stone; sum <= total/2; sum++) {
                if(dp[sum-stone]) {
                    temp[sum] = true;

                    if(sum == total/2)
                        return total - sum*2;

                    maxS2= Math.max(maxS2, sum);

                }
            }
            dp = temp;
        }


        return total - maxS2*2;
    }
}