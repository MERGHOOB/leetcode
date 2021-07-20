package leetcode_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/subsets/
https://leetcode.com/problems/subsets-ii

 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();

        backtrack(nums, 0, list, new ArrayList<>());


        return list;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> list, List<Integer> tempList) {
        list.add(List.copyOf(tempList));
        for (int i = start; i < nums.length; i++) {
//            if(i>start && nums[i] == nums[i-1]) continue; // stop duplicate
            tempList.add(nums[i]);
            backtrack(nums, i + 1, list, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int [] nums = {1};

        System.out.println(new Solution().subsets(nums));
    }
}