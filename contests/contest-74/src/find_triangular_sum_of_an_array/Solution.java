package find_triangular_sum_of_an_array;

class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(new Solution().triangularSum(nums));
    }

    public int triangularSum(int[] nums) {

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            nums[i] = (nums[i] + nums[i + 1]) % 10;
            if (i == n - 2) {
                i = -1;
                n--;
            }
        }

        return nums[0];
    }
}
