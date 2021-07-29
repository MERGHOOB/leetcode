package leetcode_count_of_smaller_elements_after_self;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionAgain {

    int[] count;

    public List<Integer> countSmaller(int[] nums) {
        // what about bst
        // any number comes, we will push that to left or right;
        // so number lies at left side will be smaller
        // and we need to also maintain count of duplicate on each node
        //
        Integer[] ans = new Integer[nums.length];
        Node root = new Node(nums[nums.length - 1]);
        for (int i = nums.length - 1; i >= 0; i--) {
            count[i] = insert(root, nums[i]);
        }
        return Arrays.asList(ans);
    }

    private int insert(Node node, int num) {
        int sum = 0;
        while (node.val != num) {
            if (node.val > num) {
                if (node.left == null) {
                    node.left = new Node(num);
                }
                node.leftSum++;
                node = node.left;
            } else {
                sum += node.leftSum + node.count;
                if (node.right == null) node.right = new Node(num);
                node = node.right;
            }
        }
        node.count++;
        return sum + node.leftSum;
    }

    static class Node {
        Node left, right;
        int val, sum, dup = 1;
        int leftSum = 0;
        int count = 0;

        Node(int val) {
            this.val = val;
        }
    }

}
