package leetcode_split_array_into_consecutive_subset;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean isPossible(int[] nums) {

        List<Integer> first  = new ArrayList<>();
        List<Integer> second = new ArrayList<>();int k  = 3;
        List<Integer> temp;
        for(int num: nums) {

            if(first.size() >= k && second.size() < k)
            {
                temp = first;
                first = second;
                second = temp;
            }


            if(first.isEmpty() || first.get(first.size()-1) != num) {
                first.add(num);
                continue;
            }

            if(second.isEmpty() || second.get(second.size()-1) != num) {
                second.add(num);
                continue;
            }

            return false;



        }
        return first.size() >2 && second.size() > 2;

    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,4,5};
        System.out.println(new Solution().isPossible(nums));
    }
}