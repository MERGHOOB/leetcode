package leetcode.lowlevelDesign.tic_tac_toe;

public class TicTacToe {
    private final int[][] board;
    private final int n;
    private final int[] rowSum, colSum;
    private int diagSum, revDiagSum;
    private int winner = 0;

    public TicTacToe(final int n) {
        this.n = n;
        board = new int[n][n];
        rowSum = new int[n];
        colSum = new int[n];
        diagSum = 0;
        revDiagSum = 0;
    }

    /**
     * Make a move on the board and returns the winner if move is a winning move
     */

    public int move(int player, int row, int col) {

        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IllegalArgumentException("Move out of board boundary");
        } else if (board[row][col] != 0) {
            throw new IllegalArgumentException("Square is already occupied");
        } else {
            player = player == 0 ? -1 : 1;
            board[row][col] = player;
            rowSum[row] += player;
            colSum[col] += player;
            if (row == col) {
                diagSum += player;
            }
            if (row == n - 1 - col) {
                revDiagSum += player;
            }
            if (Math.abs(rowSum[row]) == n || Math.abs(colSum[col]) == n
                    || Math.abs(diagSum) == n
                    || Math.abs(revDiagSum) == n) {
                winner = player;
            }


//            boolean winRow = true, winCol = true, winDiag = true, winRevDiag = true;
//
//            for (int i = 0; i < n; i++) {
//                if (board[row][i] != player) {
//                    winRow = false;
//                }
//                if (board[i][col] != player) {
//                    winCol = false;
//                }
//                if (board[i][i] != player) {
//                    winDiag = false;
//                }
//                if (board[i][n - 1 - i] != player) {
//                    winRevDiag = false;
//                }
//            }
//            if (winRevDiag || winCol || winDiag || winRow) {
//                return player;
//            }
//            return 0;
            return getWinner();
        }
    }

    public int getWinner() {
        return winner;
    }
}
