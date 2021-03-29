package weeklychallenge.week3.minimumpathsum;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 * <p>
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
class Solution {
    public int minPathSum(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean leftCellExists = i >= 1;
                boolean topCellExists = j >= 1;
                if(topCellExists && leftCellExists) {
                    grid[i][j] += Integer.min(grid[i - 1][j], grid[i][j - 1]);
                }
                else if(leftCellExists) {
                    grid[i][j] += grid[i-1][j];
                }
                else if(leftCellExists) {
                    grid[i][j] += grid[i][j-1];
                }

            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}