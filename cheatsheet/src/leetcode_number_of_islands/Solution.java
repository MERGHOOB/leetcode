package leetcode_number_of_islands;
/*
https://leetcode.com/problems/number-of-islands
 */
class Solution {
    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for(int i = 0 ; i<m; i++) {
            for(int j = 0; j<n; j++) {

                if(grid[i][j] == '1') {
                    dfs(grid, i, j,m,n);
                    count++;
                }


            }
        }

        return count;
    }

    public void dfs(char [][] grid, int i, int j, int row, int col) {
        if(i < 0 || i>=row || j < 0 || j >=col || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i, j+1, row, col);
        dfs(grid, i, j-1, row, col);
        dfs(grid, i+1, j, row, col);
        dfs(grid, i-1, j, row, col);
    }
}