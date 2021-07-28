package leetcode_relative_sort_array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        // what about get count of all numbers in arr1;
        // maximum value is 1000 and so we need 1001 size array
        int[] count = new int[1001];
        for (int num : arr1) {
            count[num]++;
        }
        int index = 0;
        for (int num : arr2) {
            while (count[num]-- > 0) {
                arr1[index++] = num;
            }
        }

        for (int i = 0; i < 1001; i++) {
            while (count[i]-- > 0) {
                arr1[index++] = i;
            }
        }
        return arr1;
    }

}
