package weeklychallenge.week5.binarytreemaxpathsum;

import utils.TreeNode;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */

/**
 * Solution: max of(root, root+leftPath, root+rightPath, root+leftPath+rightPath)
 */
class Solution {
    int max = Integer.MIN_VALUE; //max hold best result
    public int maxPathSum(TreeNode root) {
        preOrder(root); // preOrder returns best result of each subtree // max(left, right) + root
        return max;
    }

    private int preOrder(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftSum = 0;
        if(root.left != null) {
            leftSum = Integer.max(0, preOrder(root.left));
        }

        int rightSum = 0;
        if(root.right != null) {
            rightSum = Integer.max(0, preOrder(root.right));
        }
        max = Math.max(max, leftSum + root.val + rightSum);
        return Math.max(leftSum, rightSum) + root.val; // you can either choose left path or right path(based on which is max)
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        System.out.println(new Solution().maxPathSum(treeNode));
    }
}