package leetcode_max_area_of_island.Solution;

class Solution {

    private static int count;

    public int maxAreaOfIsland(int[][] grid) {


        //
        int area = 0;
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {


                if (grid[i][j] == 1) {

                    count = 0;
                    dfs(grid, i, j);
                    System.out.println(count);
                    area = Math.max(area, count);
                }


            }
        }

        return area;

    }

    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return;
        }

        count++;
        // System.out.println(count);
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);//, count);
        dfs(grid, i, j + 1);//, count);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        System.out.println(new Solution().maxAreaOfIsland(grid));
    }
}
