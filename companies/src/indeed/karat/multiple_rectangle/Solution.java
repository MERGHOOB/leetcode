package indeed.karat.multiple_rectangle;

import java.util.ArrayList;
import java.util.List;

/*
Multiple rectangles. Given a 2D array of 0s and 1s, return a list of the postions of all rectangles made up of 1s. Note: you are not allowed to change the input matrix.

# Example 1 input
grid1 =[ [0, 0, 0, 0, 0, 0, 1],
         [0, 0, 0, 0, 0, 0, 0],
         [1, 0, 0, 1, 1, 1, 0],
         [0, 1, 0, 1, 1, 1, 0],
         [0, 1, 0, 0, 0, 0, 0],
         [0, 1, 0, 1, 1, 0, 0],
         [0, 0, 0, 1, 1, 0, 0],
         [1, 0, 0, 0, 0, 0, 0] ]

# return list of (start_row, start_col, end_row, end_col)
# for each rectangle:
 [ (0, 6, 0, 6),
   (2, 0, 2, 0),
   (2, 3, 3, 5),
   (3, 1, 5, 1),
   (5, 3, 6, 4),
   (7, 0, 7, 0) ]
More examples

grid2 = [ [1] ]
# you should return [ (0,0,0,0) ]

grid3 = [ [0, 0, 0, 0, 0],
          [0, 1, 1, 1, 0],
          [0, 1, 1, 1, 0],
          [0, 1, 1, 1, 0],
          [0, 0, 0, 0, 0] ]
# you should return [ (1, 1, 3, 3) ]
 */
public class Solution {

    int rowMin = 0;
    int colMin = 0;
    int rowMax = 0;
    int colMax = 0;

    public List<List<Integer>> multipleRectangle(int[][] grid) {
        List<List<Integer>> coordinates = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, grid, visited);
                    coordinates.add(List.of(i, j, rowMax, colMax)); // adding top coordinate

                }
            }
        }
        return coordinates;
    }

    private void dfs(int rowMin, int colMin, int[][] grid, boolean[][] visited) {

        int bottomX = rowMin;
        int bottomY = colMin;

        while (bottomX < grid.length) {
            if (grid[bottomX][bottomY] == 0) {
                break;
            }
            bottomX++;
        }

        if (bottomX == grid.length || grid[bottomX][bottomY] == 0) {
            bottomX = bottomX - 1;
        }

        while (bottomY < grid[bottomX].length) {
            if (grid[bottomX][bottomY] == 0) {
                break;
            }
            bottomY++;
        }

        if (bottomY == grid[bottomX].length || grid[bottomX][bottomY] == 0) {
            bottomY = bottomY - 1;
        }
        //now make them all visited

        for (int i = rowMin; i <= bottomX; i++) {
            for (int j = colMin; j <= bottomY; j++) {
                visited[i][j] = true;
            }
        }

        rowMax = bottomX;
        colMax = bottomY;

    }

    public static void main(String[] args) {
        int[][] grid1 = {{0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0}};

        int[][] grid2 = {{1}};

        int[][] grid3 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}};

        System.out.println(new Solution().multipleRectangle(grid1));
        System.out.println(new Solution().multipleRectangle(grid2));
        System.out.println(new Solution().multipleRectangle(grid3));
    }
}
