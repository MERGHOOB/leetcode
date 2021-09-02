package leetcode_flatten_multilevel_doubly_linkedl_list;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Solution {
    public Node flatten(Node head) {
        helper(head);
        return head;
    }

    private Node helper(Node node) {
        while (node != null) {

            if (node.child != null) {
                Node next = node.next;
                Node lastChild = helper(node.child);

                node.child.prev = node;
                node.next = node.child;
                if (next != null) {
                    lastChild.next = next;
                    next.prev = lastChild;
                }
                node.child = null;
                node = lastChild;

            } else {
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
        }
        return node;
    }
}