package leetcode_game_of_life;

class SolutionWithoSquareNSpace {
    private static final int[][] DIRS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}

    };

    public void gameOfLife(int[][] board) {


        int m = board.length;
        int n = board[0].length;

        int[][] temp = new int[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                temp[i][j] = board[i][j];
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int lives = countLives(temp, i, j);
                if (temp[i][j] == 0) {

                    if (lives == 3) {
                        board[i][j] = 1;
                    }

                } else {

                    if (lives < 2) {
                        board[i][j] = 0;
                    } else if (lives > 3) {
                        board[i][j] = 0;
                    }
                }


            }
        }


    }

    public int countLives(int[][] temp, int i, int j) {

        int m = temp.length;
        int n = temp[0].length;
        int count = 0;
        int x, y;
        for (int[] dir : DIRS) {
            x = i + dir[0];
            y = j + dir[1];

            if (x < 0 || x >= m || y < 0 || y >= n || temp[x][y] == 0) {
                continue;
            }

            count++;


        }

        return count;


    }
}
