package leetcode_container_with_most_water;

class Solution {
    public int maxArea(int[] height) {

        // intuition. water is trapped between walls
        // more the height of wall, more is the water

        int left = 0, right = height.length - 1;

        int maxArea = Integer.MIN_VALUE;
        while (left < right) {

            maxArea = Math.max(maxArea, (right - left) * (Math.min(height[left], height[right])));

            if (height[left] > height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
