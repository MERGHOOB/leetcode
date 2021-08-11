package leetcode_minimum_swaps_to_make_sequence_increasing;

import java.util.Arrays;

class Solution {
    public int minSwap(int[] A, int[] B) {
        Integer[][] dp = new Integer[A.length][2];//0 no swap, 1:swap
        return dfs(A, B, 0, dp, 0);
    }

    public int dfs(int[] A, int[] B, int i, Integer[][] dp, int swap) {
        if (i == A.length) {
            return 0;
        }
        if (dp[i][swap] != null)
            return dp[i][swap];

        int min1 = Integer.MAX_VALUE;
        if (i == 0 || A[i] > A[i - 1] && B[i] > B[i - 1]) {

            // Ai > Ai-1 and Bi > Bi-1 ; it will be happy with no swap
            min1 = dfs(A, B, i + 1, dp, 0);
        }

        int min2 = Integer.MAX_VALUE;
        if (i == 0 || A[i] > B[i - 1] && B[i] > A[i - 1]) {
            // if Ai > Bi-1 and bi > ai-1 then it may require a swap as this will definitely make sure strictly increasing order
            swap(A, B, i); // swap
            min2 = dfs(A, B, i + 1, dp, 1) + 1;
            swap(A, B, i); // swap back
        }

        dp[i][swap] = Math.min(min1, min2);
        return dp[i][swap];
    }

    public int minSwapNoRecursion(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] swap = new int[n];
        int[] noSwap = new int[n];
        Arrays.fill(swap, n); //fill with max number
        Arrays.fill(noSwap, n);
        //index 0, swap or not swap
        swap[0] = 1;
        noSwap[0] = 0;


        for (int i = 1; i < n; i++) {
            //condition 1 both the same: n1[i-1] < n1[i], n2[i-1] < n2[i]
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                swap[i] = swap[i - 1] + 1; //to keep increase, must swap if i-1 swap
                noSwap[i] = noSwap[i - 1];
            }
            //condition 2 single diff: n2[i-1] < n1[i], n1[i-1] < n2[i], otherwise keep the same
            if (nums2[i - 1] < nums1[i] && nums1[i - 1] < nums2[i]) {
                swap[i] = Math.min(swap[i], noSwap[i - 1] + 1); // if no swap happened at i-1 then there is 1 swap required
                noSwap[i] = Math.min(noSwap[i], swap[i - 1]); // is swap is happened on i-1 then no swap is required
            }

        }

        return Math.min(swap[n - 1], noSwap[n - 1]);
    }

    public void swap(int[] A, int[] B, int idx) {
        int temp = A[idx];
        A[idx] = B[idx];
        B[idx] = temp;
    }
}