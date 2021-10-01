package leetcode_plus_one_linked_list;


public class Solution {
    /**
     * @param head: the first Node
     * @return: the answer after plus one
     */
    public ListNode plusOne(ListNode head) {
        // Write your code here

        ListNode node = reverse(head);

        ListNode temp = node;

        int carry = 1;
        ListNode prev = null;
        while (temp != null) {
            int x = temp.val + carry;
            if (x > 9) {
                carry = x % 9;
                temp.val = x & 1;
                prev = temp;
                temp = temp.next;

            } else {
                temp.val = x;
                carry = 0;
                break;
            }
        }
        if (carry != 0) {
            prev.next = new ListNode(carry);
        }

        return reverse(node);
    }

    public ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

}
