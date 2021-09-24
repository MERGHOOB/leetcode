package leetcode_minimum_number_of_days_to_make_m_bouquets;

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {


        if (m * k > bloomDay.length)
            return -1;

        int left = 1, right = Integer.MIN_VALUE;

        for (int b : bloomDay) {
            right = Math.max(b, right);
        }

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (isFeasible(bloomDay, mid, m, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }


        }

        return left;


    }


    private boolean isFeasible(int[] blooms, int cand, int m, int k) {

        int total = 0;
        int bouquet = 0;

        for (int b : blooms) {

            if (b <= cand) {
                total++;
            } else {
                if (total >= k) {
                    bouquet++;
                }
                total = 0;
            }

            if (total >= k) {
                bouquet++;
                total = 0;
            }

        }

        return bouquet >= m;


    }
}