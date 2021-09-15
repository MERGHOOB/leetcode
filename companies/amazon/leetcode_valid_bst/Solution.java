package leetcode_valid_bst;

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
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }

        if (root.right != null && root.right.val <= root.val) {
            return false;
        }

        return check(Long.MIN_VALUE, root.left, root.val) &&
                check(root.val, root.right, Long.MAX_VALUE);


    }


    private boolean check(long min, TreeNode node, long max) {

        if (node == null) return true;

        if (node.left != null && (node.left.val <= min || node.left.val >= node.val)) {
            return false;
        }

        if (node.right != null && (node.right.val >= max || node.right.val <= node.val)) {
            return false;
        }


        return check(min, node.left, node.val) && check(node.val, node.right, max);

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