package partition_to_k_equal_subsets;

import java.util.Arrays;

/*
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */
class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int targetBucketSum = sum / k;

        Arrays.sort(nums);
        if (nums[nums.length - 1] > targetBucketSum) {
            return false;
        }

        boolean[] visited = new boolean[nums.length];

        return fillBucket(nums, 0, k, 0, targetBucketSum, visited);

    }

    private boolean fillBucket(int[] nums, int start, int k, int currentBucketSum, int targetBucketSum, boolean[] visited) {
        if (k == 1) { // if this is last bucket remaining will element will be equal targetBucketSum
            return true;
        }

        if (currentBucketSum == targetBucketSum) {
            return fillBucket(nums, 0, k - 1, 0, targetBucketSum, visited); // now fill k-1 th bucket
        }

        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) { // if i th element is picked up
                visited[i] = true; // pick this
                //and check whether it can be part of bucket element
                if (fillBucket(nums, i + 1, k, currentBucketSum + nums[i], targetBucketSum, visited)) {
                    return true;
                }
                //failed to be a part of bucket,
                visited[i] = false;
            }
        }

        return false;
    }

    public boolean canPartitionKSubsetsUsingBits(int[] nums, int k) {

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int targetBucketSum = sum / k;

        Arrays.sort(nums);
        if (nums[nums.length - 1] > targetBucketSum) {
            return false;
        }

        return loop(nums, k, new Bits(), 0, 0, targetBucketSum);

    }

    private boolean loop(int[] nums, int k, Bits bits, int start, int currentBucketSum, int targetBucketSum) {

//         # Since number of buckets is only reduced when the previous bucket has been filled,
//            # we only need to check if all elements have been used. No need to check current sum.
        if (k == 0) {
            return bits.isAllSet();
        }
        if (currentBucketSum == targetBucketSum) {//Fill remaining buckets.
            return loop(nums, k - 1, bits, 0, 0, targetBucketSum);
        }
//      Find all unused numbers that can be added to the current bucket.
        for (int i = start; i < nums.length; i++) {
            if (bits.isUnSet(i) && nums[i] + currentBucketSum <= targetBucketSum) {
                bits.set(i);
                if (loop(nums, k, bits, i + 1, currentBucketSum + nums[i], targetBucketSum)) {
                    return true;
                }
                bits.clear(i);
            }
        }
        return false;
    }

    class Bits {
        int mask = 0;

        boolean isUnSet(int i) {
            // (1 << i) == All bits cleared except the i-th bit
            return (mask & (1 << i)) == 0;
        }

        void set(int i) {
            //(1 << i) == All bits cleared except the i-th bit
            mask |= (1 << i);
        }

        void clear(int i) {
            // ~(1<<i) == All bits set except i;
            mask &= ~(1 << i);
        }

        boolean isAllSet() {
            if (mask == 0) {
                return false;
            }
//         If all bits of a number are set, then adding 1 to it produces a number that is a perfect power of 2.
//         AND-ing it with the number clears all bits of the number.
            return (mask & (mask + 1)) == 0;
        }
    }
}
