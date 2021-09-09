package leetcode_game_of_life;

class Solution {

    private static final int[][] DIRS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}

    };

    public void gameOfLife(int[][] board) {

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int lives = countLives(board, m, n, i, j);
                if (board[i][j] == 0) {
                    if (lives == 3) {
                        board[i][j] = 3;
                    }
                } else {
                    if (lives < 2 || lives > 3) {
                        board[i][j] = -1;
                    }
                }

            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 3) board[i][j] = 1;
                else if (board[i][j] == -1) board[i][j] = 0;
            }
        }

    }

    private int countLives(int[][] board, int m, int n, int i, int j) {
        int count = 0;
        int x, y;
        for (int[] dir : DIRS) {
            x = i + dir[0];
            y = j + dir[1];

            if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 0 || board[x][y] == 3) {
                continue;
            }

            count++;


        }

        return count;
    }
}
