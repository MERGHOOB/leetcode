package leetcode_merge_sorted_arrays;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int index = m+n -1;
        while( m >0 && n > 0) {
            if(nums1[m-1] > nums2[n-1]) {
                nums1[index--] = nums1[m-1];
                m--;
            }
            else {
                nums1[index--] = nums2[n-1];
                n--;
            }
        }
        while(n>0) {
            nums1[index--] = nums2[n-1];
            n--;
        }


    }
}