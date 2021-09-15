package leetcode_populating_next_right_pointers_in_each_node;

/*

 */

class Solution {


    @SuppressWarnings("UnusedReturnValue")
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }

        if (root.right != null) {
            root.right.next = root.next != null ? root.next.left : null;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }

    // Definition for a Node.
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;
}