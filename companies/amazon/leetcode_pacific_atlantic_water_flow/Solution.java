package leetcode_pacific_atlantic_water_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private final int[][] DIRS = {
            {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;
        // can go to pacific ocean
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        //anything from first row can go to pacific
        //anything from last row can go to atalantic
        for (int j = 0; j < n; j++) {
            dfs(heights, 0, j, pacific);
            dfs(heights, m - 1, j, atlantic);
        }

        // first column is pacific and last column is Atlantic

        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific);
            dfs(heights, i, n - 1, atlantic);
        }


        // get intersection of both

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (atlantic[i][j] && pacific[i][j]) {
                    res.add(Arrays.asList(i, j));
                }


            }
        }


        return res;


    }

    public void dfs(int[][] heights, int i, int j, boolean[][] ocean) {

        ocean[i][j] = true;

        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0
                    || x >= heights.length
                    || y < 0
                    || y >= heights[x].length
                    || (heights[x][y] < heights[i][j])
                    || ocean[x][y]
            ) {
                continue;
            }

            dfs(heights, x, y, ocean);
        }


    }

}