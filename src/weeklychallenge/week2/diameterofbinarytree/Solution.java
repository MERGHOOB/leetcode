package weeklychallenge.week2.diameterofbinarytree;


import utils.TreeNode;

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
    int diameter = -1;
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {

        int leftHeight = 0;
        if (root.left != null) {
            leftHeight = 1 + height(root.left);
        }
        int rightHeight = 0;
        if (root.right != null) {
            rightHeight = 1 + height(root.right);
        }

        diameter = Integer.max(rightHeight + leftHeight, diameter);
        return Integer.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(4);

        int i = new Solution().diameterOfBinaryTree(root);
        System.out.println(i);

    }
}