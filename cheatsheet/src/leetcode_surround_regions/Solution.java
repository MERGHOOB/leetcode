package leetcode_surround_regions;
/*
https://leetcode.com/problems/surrounded-regions/submissions/
 */
class Solution {

    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] visited;
    char o = 'O';

    public void solve(char[][] grid) {

        visited = new int[grid.length][grid[0].length];

        // will visit 1st row, last row, 1st column, last column

        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == o) {
                dfs(grid, visited, 0, j); // this will mark everything as 1 into visited array
            }
            if (grid[grid.length - 1][j] == o) {
                dfs(grid, visited, grid.length - 1, j);
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == o) {
                dfs(grid, visited, i, 0);
            }
            if (grid[i][grid[i].length - 1] == o) {
                dfs(grid, visited, i, grid[i].length - 1);
            }
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (visited[i][j] == 1) {
                    continue;
                }
                grid[i][j] = 'X';

            }
        }
    }

    public void dfs(char[][] grid, int[][] visited, int i, int j) {
        visited[i][j] = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0
                    || x >= grid.length
                    || y < 0
                    || y >= grid[x].length
                    || grid[x][y] == 'X'
                    || visited[x][y] == 1
            ) {
                continue;
            }
            dfs(grid, visited, x, y);
        }

    }
}
