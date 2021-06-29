package assesments.amazon.TreasureIsland;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    public static int minSteps(char[][] grid) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0));
        grid[0][0] = 'D';
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            for (int[] dir : DIRS) {
                int x = poll.r + dir[0];
                int y = poll.c + dir[1];
                if (!isSafe(grid, x, y)) {
                    continue;
                }
                if (grid[x][y] == 'X') {
                    return poll.level + 1;
                } else if (grid[x][y] == 'O') {
                    queue.add(new Point(x, y, poll.level + 1));
                    grid[x][y] = 'D';
                }
            }
        }
        return -1;
    }

    private static boolean isSafe(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length;
    }

    private static class Point {
        int level;
        int r;

        public Point(int r, int c, int level) {
            this.r = r;
            this.c = c;
            this.level = level;
        }

        int c;

    }

    public static void main(String[] args) {
        char[][] grid =
                {{'O', 'O', 'O', 'O'},
                        {'D', 'D', 'D', 'D'},
                        {'O', 'O', 'O', 'O'},
                        {'X', 'D', 'D', 'O'}};
        System.out.println(minSteps(grid));
    }
}
