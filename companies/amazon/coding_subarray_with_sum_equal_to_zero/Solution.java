package coding_subarray_with_sum_equal_to_zero;

import java.util.HashSet;
import java.util.Set;

/*
 Find if there is a subarray with 0 sum
 {1, 2, 3, 1, 6} => False
  {-5, -4, 4, 5, 8, 6} => True {-5, -4, 4, 5}
  {4, -1, -3, 1, 6} => True {4, -1, -3}

 */
class Solution {

    public boolean isSubarrayWithSumEqualToZeroExists(int[] array) {

        //{-5, -4, 4, 5, 8, 6}
        // -5, -9, -5, 0, 8,14

        // Iterate from 0 and add a cumulative sum if a sum is encounter again as -5 comes again it means from 1 to 2 is sum = 0;
        // or cumulitve become zero at any index say x then from 0 to x ; sum is zero

        int sum = 0;
        Set<Integer> sumMap = new HashSet<>();

        for (int j : array) {

            sum += j;
            if (sum == 0 || sumMap.contains(sum)) {
                return true;
            }
            sumMap.add(sum);

        }

        return false;


    }
}
