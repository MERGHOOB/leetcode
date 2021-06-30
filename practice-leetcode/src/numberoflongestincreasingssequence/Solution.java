package numberoflongestincreasingssequence;

import java.util.Arrays;

/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */
public class Solution {
    public int findNumberOfLIS(int[] nums) {


        int[] len = new int[nums.length]; // len of lis ending at this indexed number
        int[] count = new int[nums.length]; // numbs of LIS ending at nums[i];
        Arrays.fill(len, 1);
        Arrays.fill(count, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] + 1) {
                        count[i] += count[j]; // it means it is going to append to all lis which are ending to nums[j]
                    } else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        count[i] = count[j]; // new
                    }
                }
            }
        }
        int number = 0;
        int length = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {

            if (len[i] > length) {
                length = len[i];
                number = count[i];
            } else if (len[i] == length) {
                number += count[i];
            }

        }
        return number;

    }
}
