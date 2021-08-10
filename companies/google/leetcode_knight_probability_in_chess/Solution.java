package leetcode_knight_probability_in_chess;
/*
https://leetcode.com/problems/knight-probability-in-chessboard
 */
class Solution {

    private int[][] dirs = {
            {2,1},
            {2,-1},
            {-2,1},
            {-2,-1},
            {1,2},
            {1,-2},
            {-1,2},
            {-1,-2}
    };

    public double knightProbability(int n, int k, int row, int column) {
        double [][][] dp = new double[n][n][k+1];
        return find(n,k,row, column, dp) ;
    }


    public double find(int n, int k, int r, int c, double[][][]dp) {
        if( r < 0 || r >= n || c < 0 || c>=n) {
            return 0;
        }
        if(k == 0) {
            return 1;
        }
        if(dp[r][c][k] != 0) return dp[r][c][k];
        double rate = 0;
        for(int [] dir: dirs) {
            int x = r + dir[0];
            int y = c + dir[1];
            rate += 0.125*find(n, k-1, x, y,dp);
        }
        dp[r][c][k] = rate;
        return rate;
    }
}