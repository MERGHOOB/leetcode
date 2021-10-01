package lintcode_find_leaves_of_binary_tree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        level(root, result);
        return result;
    }

    public int level(TreeNode root, List<List<Integer>> result) {

        if (root == null) return -1;
        int level = Math.max(level(root.left, result), level(root.right, result)) + 1;
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        return level;
    }

    private static class TreeNode {
        public TreeNode left, right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
}