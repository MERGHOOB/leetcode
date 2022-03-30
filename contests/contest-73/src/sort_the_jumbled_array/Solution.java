package sort_the_jumbled_array;

import java.util.Arrays;
import java.util.Comparator;

class Solution {

    public int[] sortJumbled(int[] mapping, int[] nums) {

       Integer [] integers = new Integer[nums.length];
       for(int i = 0; i<nums.length; i++) {
           integers[i] = nums[i];
       }
       Arrays.sort(integers, Comparator.comparing(e -> getMapping(e, mapping)));
        for(int i = 0; i<nums.length; i++) {
            nums[i] = integers[i];
        }
       return nums;
    }

    private Integer getMapping(Integer value, int[] mapping) {
        if(value == 0) {
            return mapping[value];
        }
        int res = 0;
        int i = 1;
        while (value != 0 ) {
            int rem = value % 10;
            res = res + mapping[rem] * i;
            i = i * 10;
            value = value / 10;
        }
        return res;
    }


    public static void main(String[] args) {
        int [] mapping = {9,8,7,6,5,4,3,2,1,0};
        int [] nums = {0,1,2,3,4,5,6,7,8,9};

        int[] x = new Solution().sortJumbled(mapping, nums);
        System.out.println(x);
    }

}
