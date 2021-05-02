package assesments.ms.partiontokequalsumsubsets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (k > nums.length) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;

        Arrays.sort(nums);
        Map<Integer, Boolean> memo = new HashMap<>();
        return dfs(nums, new boolean[nums.length], k, 0, sum / k, nums.length - 1, memo);
    }

    private boolean dfs(int[] nums, boolean[] visited, int k, int currentSum, int targetSum, int position, Map<Integer, Boolean> memo) {
        if (k == 0) {
            // i.e nothing remains unvisited i.e successfull
            return true;
        }
        // begin next sum search
        if (currentSum == targetSum) {
            //Critical point: start search from nums.length - 1, not position!!!
            return dfs(nums, visited, k - 1, 0, targetSum, nums.length - 1, memo);
        }
        Integer sumTillPosition = getSumTillPosition(nums, position);
        if (memo.containsKey(sumTillPosition)) {
            return false;
        }

        for (int i = position; i >= 0; i--) {
            // skip logic-1: if visited already skip
            if (visited[i]) continue;

            //skip logic 2:
            // if the last postion i+1 is not visited: that it does not work for current combination
            // and for value at position i+1 value is a problem
            if (i + 1 < nums.length && nums[i + 1] == nums[i] && !visited[i + 1]) {
                continue;
            }

            if (currentSum + nums[i] > targetSum) continue;

            visited[i] = true;
            if (dfs(nums, visited, k, currentSum + nums[i], targetSum, i - 1, memo)) {
                return true;
            }
            visited[i] = false;
        }

        memo.put(sumTillPosition, false);
        return false;
    }

    private Integer getSumTillPosition(int[] nums, int position) {
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        IntStream.range(0, position + 1).forEach(i -> {
            sum.updateAndGet(v -> v + nums[i]);
        });
        return sum.get();
    }


}