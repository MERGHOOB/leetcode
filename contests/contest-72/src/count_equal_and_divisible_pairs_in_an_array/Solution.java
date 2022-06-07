package count_equal_and_divisible_pairs_in_an_array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int countPairs(int[] nums, int k) {

        int res = 0;
        for(int i = 0; i<nums.length-1; i++) {
            for(int j = i+1; j<nums.length; j++) {
                if(nums[i]== nums[j] && (i*j)%k ==0 ) {
                    res++;
                }
            }
        }
        return res;
        /*
        int res = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        for (Integer key : map.keySet()) {

            List<Integer> indexes = map.get(key);
            if (indexes.size() == 1) {
                continue;
            }

            for (int i = 0; i < indexes.size() - 1; i++) {
                for (int j = i + 1; j < indexes.size(); j++) {

                    if ((indexes.get(i) * indexes.get(j)) % k == 0) {
                        res++;
                    }
                }
            }


        }

        return res;

         */
    }

    public static void main(String[] args) {
        int [] nums = {3,1,2,2,2,1,3};
        int k = 2;
        System.out.println(new Solution().countPairs(nums, k));
    }
}
