package leetcode_diameter_of_tree;

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

    int d = 0;


    public int diameterOfBinaryTree(TreeNode root) {

        helper(root);
        return d;


    }

    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 0, right = 0;

        if (root.left != null) {
            left = 1 + helper(root.left);
        }
        if (root.right != null) {
            right = 1 + helper(root.right);
        }

        d = Math.max(left + right, d);

        return Math.max(left, right);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
