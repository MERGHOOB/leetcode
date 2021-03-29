package weeklychallenge.week3.numberofislands;

class Solution {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '0') {
                    continue;
                }
                count++;
                visitNeighbours(grid, i, j);
            }
        }
        return count;
    }

    private void visitNeighbours(char[][] grid, int i, int j) {

        grid[i][j] = '0';
        int[] it = {-1, 0, 1, 0};
        int[] jt = {0, -1, 0, 1};
        for (int k = 0; k < 4; k++) {
            int neighbourI = i + it[k];
            int neighbourJ = j + jt[k];
            if ((neighbourI < 0 || neighbourI >= grid.length)
                    || (neighbourJ < 0 || neighbourJ >= grid[neighbourI].length)
                    ||  grid[neighbourI][neighbourJ] == '0') {
                continue;
            }
            visitNeighbours(grid, neighbourI, neighbourJ);
        }
    }
}
