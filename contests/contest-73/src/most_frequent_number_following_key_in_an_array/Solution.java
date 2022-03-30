package most_frequent_number_following_key_in_an_array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
2 <= nums.length <= 1000
1 <= nums[i] <= 1000
The test cases will be generated such that the answer is unique.
 */
class Solution {
    public int mostFrequent(int[] nums, int key) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != key) {
                continue;
            }
            map.put(nums[i + 1], map.getOrDefault(nums[i+1], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toList());
        return collect.get(collect.size()-1).getKey();

    }

    public static void main(String[] args) {
        int [] nums= {2,1000,2,1000,2,3};
        int key = 2;
        System.out.println(new Solution().mostFrequent(nums,key));
    }
}
