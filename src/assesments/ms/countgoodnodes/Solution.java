package assesments.ms.countgoodnodes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
        }

    }

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        int max = 0;
        return dfs(root, max);
//        return count;

    }

    private int dfs(TreeNode root, int max) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        if(root.val >= max) {
            max = root.val;
            count++;
        }
        count = count + dfs(root.left, max) +
        dfs(root.right, max);
        return count;

    }
}
