package last_stone_weight_II;

class Solution {
    public int lastStoneWeightII(int[] stones) {

        int sumOfStonesWeight = 0;
        for (int stone : stones) {
            sumOfStonesWeight += stone;
        }

        int max_possible_weightOfNegativeSum = sumOfStonesWeight >> 1;

        int dpRow = stones.length + 1;
        boolean[][] dp = new boolean[dpRow][max_possible_weightOfNegativeSum + 1];

        for (int i = 0; i < dpRow; i++) {
            dp[i][0] = true;
        }

        int maxNegativeSum = 0;
        for (int i = 1; i < dpRow; i++) {
            for (int j = 1; j <= max_possible_weightOfNegativeSum; j++) {

                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i-1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - stones[i-1]];
                }
                if (dp[i][j]) {
                    maxNegativeSum = Math.max(maxNegativeSum, j);
                }

            }
        }
        return sumOfStonesWeight - (maxNegativeSum <<1);
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(new Solution().lastStoneWeightII(stones));
    }
}