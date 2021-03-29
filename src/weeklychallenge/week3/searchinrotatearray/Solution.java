package weeklychallenge.week3.searchinrotatearray;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is guaranteed to be rotated at some pivot.
 * -104 <= target <= 104
 * <p>
 * <p>
 * Follow up: Can you achieve this in O(log n) time complexity?
 */
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        int pivot = findPivot(nums, low, high);
        if (pivot == -1) {
            return binarySearch(nums, low, high, target);
        }
        if (nums[pivot] == target) {
            return pivot;
        }
        if (target >= nums[low] && target < nums[pivot]) {
            return binarySearch(nums, low, pivot - 1, target);
        } else if (pivot+1 <= high && target >= nums[pivot + 1] && target <= nums[high]) {
            return binarySearch(nums, pivot + 1, high, target);
        }
        return -1;
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private int findPivot(int[] nums, int low, int high) {
        if (high < low) {
            return -1;
        }
        if (high == low) {
            return low;
        }
        int mid = (low + high) / 2;

        if (mid < high && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        if (mid > low && nums[mid] < nums[mid - 1]) {
            return mid - 1;
        }
        if (nums[low] >= nums[mid]) {
            return findPivot(nums, low, mid - 1);
        }
        return findPivot(nums, mid + 1, high);
    }

    public static void main(String[] args) {
        int[] nums = {3,1};
        int search = new Solution().search(nums, 1);
        System.out.println(search);
    }
}