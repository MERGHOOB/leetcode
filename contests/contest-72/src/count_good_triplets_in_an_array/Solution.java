package count_good_triplets_in_an_array;

class Solution {

    public long goodTriplets(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        FenwickTree fenwickTree = new FenwickTree(n + 1);

        long[] left = new long[n];
        long[] right = new long[n];

        // calcutate left;
        for (int i = 0; i < n; i++) {
            int idx = pos[nums1[i]];
            left[i] = fenwickTree.sum(idx - 1); // can't incldue idx in left as it is mid
            fenwickTree.update(idx, 1);
        }
        //calucate right
        fenwickTree = new FenwickTree(n + 1);
        for (int i = n - 1; i >= 0; i--) {
            int idx = pos[nums1[i]];
            right[i] = fenwickTree.sum(n + 1) - fenwickTree.sum(idx);
            fenwickTree.update(idx, 1);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans += (left[i] * right[i]);
        }

        return ans;


    }

    private static class FenwickTree {
        int[] bit;
        int n;

        public FenwickTree(int n) {
            this.n = n;
            this.bit = new int[n + 2];
        }


        public void update(int i, int val) {
            i++;
            while (i < bit.length) {
                bit[i] += val;
                i += (i & (-i));

            }
        }

        int sum(int i) {
            i++;
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= (i & (-i));
            }
            return res;
        }


    }
}
