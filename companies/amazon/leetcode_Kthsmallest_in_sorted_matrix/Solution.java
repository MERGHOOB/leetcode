package leetcode_Kthsmallest_in_sorted_matrix;

import java.util.PriorityQueue;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {


        int n = matrix.length;


        PriorityQueue<Triplet> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (int i = 0; i < n; i++) {
            pq.add(new Triplet(matrix[i][0], i, 0));

        }

        int count = 0;
        while (!pq.isEmpty()) {
            Triplet temp = pq.poll();
            count++;
            int val = temp.val;
            int row = temp.row;
            int col = temp.col;

            if (count == k) {
                return val;
            }
            if (col < n - 1) {
                pq.add(new Triplet(matrix[row][col + 1], row, col + 1));
            }

        }


        return -1;
    }

    private static class Triplet {
        int val;
        int row;
        int col;

        Triplet(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }
}