package leetcode_binary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> first = new ArrayList<>();
        first.add(root);
        List<TreeNode> second = new ArrayList<>();

        while (!first.isEmpty()) {
            second.clear();
            List<Integer> temp = new ArrayList<>();

            for (TreeNode node : first) {
                temp.add(node.val);
                if (node.left != null) second.add(node.left);
                if (node.right != null) second.add(node.right);

            }

            res.add(temp);

            List<TreeNode> set = first;
            first = second;
            second = set;
        }

        return res;
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