package leetcode_island_perimeter;
/*
https://leetcode.com/problems/island-perimeter

 */
class Solution {
    static int count = 0;
    public int islandPerimeter(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        count = 0;
        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {

                if(grid[i][j] == 1) {
                    dfs(grid, i, j, m, n);
                }
            }
        }

        return count;

    }

    void dfs(int [][] grid, int i , int j , int m, int n) {
        if( i < 0 || i>=m || j<0 || j>=n || grid[i][j] == 0 ) {
            count++;return;
        }
        if(grid[i][j] ==2) {
            return;
        }
        grid[i][j] = 2;

        dfs(grid, i,j+1, m,n);
        dfs(grid, i, j-1, m,n);
        dfs(grid, i+1,j,m,n);
        dfs(grid, i-1,j,m,n);
    }
}
