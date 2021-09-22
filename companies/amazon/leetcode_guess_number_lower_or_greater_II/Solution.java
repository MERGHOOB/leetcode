package leetcode_guess_number_lower_or_greater_II;

class Solution {

    public int getMoneyAmount(int n) {

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = i;
        }

        for (int i = 2; i < n; i++) {

            int x = 0;
            int y = i;

            while (y <= n) {

                dp[x][y] = Integer.MAX_VALUE;
                for (int k = x + 1; k < y; k++) {
                    dp[x][y] = Math.min(dp[x][y], k + Math.max(dp[x][k - 1], dp[k + 1][y]));
                }

                dp[x][y] = Math.min(dp[x][y], x + dp[x + 1][y]);
                dp[x][y] = Math.min(dp[x][y], y + dp[x][y - 1]);


                x++;
                y++;
            }


        }

        return dp[1][n];


    }
    /*
    public int getMoneyAmount(int n) {


        if(n<=1) return 0;

        int [][]dp = new int[n+1][n+1];

        return explore(dp, 1, n);
    }

    private int explore(int [][] dp, int start, int end) {

        if(start >= end) return 0;

        if(dp[start][end] > 0) return dp[start][end];

        int minCost = Integer.MAX_VALUE;
        for(int k = start; k<=end; k++) {

            int cost = k + Math.max(explore(dp, start, k-1), explore(dp, k+1, end));
            minCost = Math.min(minCost, cost);
        }

        dp[start][end] = minCost;
        return minCost;


    }
    */
}