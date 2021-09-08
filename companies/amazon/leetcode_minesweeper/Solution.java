package leetcode_minesweeper;

class Solution {
    private int[][] DIRS = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        expand(board, board.length, board[0].length, click[0], click[1]);
        return board;

    }

    void expand(char[][] board, int rows, int cols, int x, int y) {


        if (board[x][y] != 'E') {
            return;
        }

        int mines = countMines(board, rows, cols, x, y);

        if (mines > 0) {
            board[x][y] = (char) ('0' + mines);
        } else {

            board[x][y] = 'B';

            for (int[] dir : DIRS) {

                int r = x + dir[0];
                int c = y + dir[1];

                if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 'E') {
                    expand(board, rows, cols, r, c);

                }


            }


        }


    }

    private int countMines(char[][] board, int rows, int cols, int x, int y) {
        int count = 0;
        for (int[] dir : DIRS) {

            int r = x + dir[0];
            int c = y + dir[1];

            if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 'M') {
                count++;
            }

        }
        return count;
    }

}