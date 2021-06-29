package largestdivisibleset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/largest-divisible-subset/
 */
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {

        Arrays.sort(nums); // for each number, all divisor lies before it

        int[] divCount = new int[nums.length];
        Arrays.fill(divCount, 1);

        //// maintain the index of the last increment
        int[] prev = new int[nums.length];
        Arrays.fill(prev, -1);
        //index at which last increment happened
        int maxIndex = 0;
        //
        for (int i = 0; i < nums.length; i++) {

            for (int j = i - 1; j >= 0; j--) {

                /// only increment the maximum index if
                //                // this iteration will increase it
                if (nums[i] % nums[j] == 0
                        && divCount[i] < divCount[j] + 1) {
                    divCount[i] = divCount[j] + 1;
                    prev[i] = j; // store previous of i; as it is increment and we want result
                }
            }

            // if divcount[i] is more than maxIndex; this will be largest
            maxIndex = divCount[i] > divCount[maxIndex] ? i : maxIndex;
        }

        List<Integer> result = new ArrayList<>();
        int k = maxIndex;
        while (k >= 0) {
            result.add(nums[k]);
            k = prev[k];
        }

        return result;
    }
}
