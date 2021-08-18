package leetcode_longest_increasing_path_in_a_matrix;
/*
https://leetcode.com/problems/longest-increasing-path-in-a-matrix
 */
class Solution {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] cache = new int[m][n];

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, cache);
                maxLen = Math.max(maxLen, len);

            }
        }

        return maxLen;
    }

    int dfs(int[][] matrix, int i, int j, int[][] cache) {


        if (cache[i][j] != 0)
            return cache[i][j];
        int res = 1;
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni < 0
                    || ni >= matrix.length
                    || nj < 0
                    || nj >= matrix[i].length
                    || matrix[ni][nj] <= matrix[i][j]) {

                continue;
            }

            res = Math.max(res, 1 + dfs(matrix, ni, nj, cache));


        }

        cache[i][j] = res;
        return res;

    }

}