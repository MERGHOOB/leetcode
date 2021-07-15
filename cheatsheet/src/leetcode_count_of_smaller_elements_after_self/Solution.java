package leetcode_count_of_smaller_elements_after_self;

import java.util.ArrayList;
import java.util.List;



/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
https://leetcode.com/problems/count-of-smaller-numbers-after-self/


Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Example 2:

Input: nums = [-1]
Output: [0]


 */

class Solution {
    int[] count;

    public List<Integer> countSmaller(int[] nums) {

        List<Integer> res = new ArrayList<>();

        count = new int[nums.length];

        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        // initially no change in order

        // do the mergesort
        mergesort(nums, indexes, 0, nums.length - 1);

        for (int i = 0; i < count.length; i++) {
            res.add(count[i]);
        }
        return res;

    }

    private void mergesort(int[] nums, int[] indexes, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = (start + end) >> 1;

        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, mid, end);


    }

    private void merge(int[] nums, int[] indexes, int start, int mid, int end) {

        int leftS = start;
        int leftE = mid;
        int rightS = mid + 1;
        int rightE = end;

        int[] mergedI = new int[end - start + 1];
        int i = 0;
        int rightSmallerCount = 0;
        while (leftS <= leftE && rightS <= rightE) {
            if (nums[indexes[leftS]] > nums[indexes[rightS]]) {
                rightSmallerCount++;
                mergedI[i++] = indexes[rightS++];
            } else {
                count[indexes[leftS]] += rightSmallerCount;
                mergedI[i++] = indexes[leftS++];
            }
        }

        while (leftS <= leftE) {
            count[indexes[leftS]] += rightSmallerCount;
            mergedI[i++] = indexes[leftS++];
        }

        while (rightS <= rightE) {
            mergedI[i++] = indexes[rightS++];
        }

        // update indexes array
        for (i = start; i <= end; i++) {
            indexes[i] = mergedI[i - start];
        }


    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};

        System.out.println(new Solution().countSmaller(nums));
    }
}