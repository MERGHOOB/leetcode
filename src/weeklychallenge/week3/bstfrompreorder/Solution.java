package weeklychallenge.week3.bstfrompreorder;

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
    public TreeNode bstFromPreorder(int[] preorder) {

        if (preorder == null) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];

            insert(root, new TreeNode(val));

        }
        return root;
    }

    private void insert(TreeNode head, TreeNode newNode) {
        if (newNode.val < head.val) {
            if (head.left == null) {
                head.left = newNode;
            } else {
                insert(head.left, newNode);
            }
        } else {
            if (head.right == null) {
                head.right = newNode;
            } else {
                insert(head.right, newNode);
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = {8, 5, 1, 7, 10, 12};
        new Solution().bstFromPreorder(ints);
    }
}